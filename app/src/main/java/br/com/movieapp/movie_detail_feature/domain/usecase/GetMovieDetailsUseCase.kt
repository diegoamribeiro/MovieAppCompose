package br.com.movieapp.movie_detail_feature.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.domain.MovieDetails
import br.com.movieapp.core.util.ResourceData
import br.com.movieapp.movie_detail_feature.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface GetMovieDetailsUseCase {
    operator fun invoke(params: Params) : Flow<ResourceData<Pair<Flow<PagingData<Movie>>, MovieDetails>>>
    data class Params(val movieId: Int)
}

class GetMovieDetailsUseCaseImpl @Inject constructor(
    private val repository: MovieDetailsRepository
) :GetMovieDetailsUseCase {
    override fun invoke(params: GetMovieDetailsUseCase.Params): Flow<ResourceData<Pair<Flow<PagingData<Movie>>, MovieDetails>>> {
        return flow {
            try {
                emit(ResourceData.Loading)
                val movieDetails = repository.getMovieDetails(params.movieId)
                val moviesSimilar = repository.getMoviesSimilar(
                    movieId = params.movieId,
                    pagingConfig = PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
                emit(ResourceData.Success(moviesSimilar to movieDetails))
            }catch (exception: IOException){
                emit(ResourceData.Fail(exception))
            }catch (exception: HttpException){
                emit(ResourceData.Fail(exception))
            }
        }.flowOn(Dispatchers.IO)
    }

}