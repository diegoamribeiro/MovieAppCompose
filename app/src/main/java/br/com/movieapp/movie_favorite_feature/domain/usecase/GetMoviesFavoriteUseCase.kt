package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.util.ResourceData
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetMoviesFavoriteUseCase {
    suspend operator fun invoke() : Flow<List<Movie>>
}

class GetMoviesFavoriteUseCaseImpl @Inject constructor(
    private val movieFavoriteRepository: MovieFavoriteRepository
) : GetMoviesFavoriteUseCase {
    override suspend fun invoke(): Flow<List<Movie>> {
        return movieFavoriteRepository.getMovies()
    }
}