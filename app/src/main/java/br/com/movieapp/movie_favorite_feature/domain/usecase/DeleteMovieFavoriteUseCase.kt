package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.util.ResourceData
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface DeleteMovieFavoriteUseCase {
    suspend operator fun invoke(params: Params) : Flow<ResourceData<Unit>>
    data class Params(val movie: Movie)
}

class DeleteMovieFavoriteUseCaseImpl @Inject constructor(
    private val movieFavoriteRepository: MovieFavoriteRepository
) : DeleteMovieFavoriteUseCase {
    override suspend fun invoke(params: DeleteMovieFavoriteUseCase.Params): Flow<ResourceData<Unit>> {
        return flow {
            val delete = movieFavoriteRepository.delete(params.movie)
            emit(ResourceData.Success(delete))
        }.flowOn(Dispatchers.IO)
    }
}