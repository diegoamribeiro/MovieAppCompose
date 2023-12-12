package br.com.movieapp.search_movie_feature.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.MovieSearch
import br.com.movieapp.search_movie_feature.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SearchMovieUseCase {
    operator fun invoke(params: Params) : Flow<PagingData<MovieSearch>>
    data class Params(val query: String)
}
class SearchMovieUseCaseImpl @Inject constructor(
    private val repository: MovieSearchRepository
) : SearchMovieUseCase{
    override fun invoke(params: SearchMovieUseCase.Params): Flow<PagingData<MovieSearch>> {
        return repository.searchMovies(
            query = params.query,
            pagingConfig = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            )
        )
    }

}