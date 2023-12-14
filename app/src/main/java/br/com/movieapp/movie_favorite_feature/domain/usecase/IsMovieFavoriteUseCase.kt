package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.util.ResourceData
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface IsMovieFavoriteUseCase {
    suspend operator fun invoke(params: Params) : Flow<ResourceData<Boolean>>
    data class Params(val movieId: Int)
}

class IsMovieFavoriteUseCaseImpl @Inject constructor(
    private val movieFavoriteRepository: MovieFavoriteRepository
) : IsMovieFavoriteUseCase {
    override suspend fun invoke(params: IsMovieFavoriteUseCase.Params): Flow<ResourceData<Boolean>> {
        return flow {
            val isFavorite = movieFavoriteRepository.isFavorite(params.movieId)
            emit(ResourceData.Success(isFavorite))
        }.flowOn(Dispatchers.IO)
    }
}