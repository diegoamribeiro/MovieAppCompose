package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.util.ResourceData
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface AddMovieFavoriteUseCase {
    suspend operator fun invoke(params: Params) : Flow<ResourceData<Unit>>
    data class Params(val movie: Movie)
}

class AddMovieFavoriteUseCaseImpl @Inject constructor(
    private val movieFavoriteRepository: MovieFavoriteRepository
) : AddMovieFavoriteUseCase {
    override suspend fun invoke(params: AddMovieFavoriteUseCase.Params): Flow<ResourceData<Unit>> {
        return flow {
            val insert = movieFavoriteRepository.insert(params.movie)
            emit(ResourceData.Success(insert))
        }.flowOn(Dispatchers.IO)
    }
}