package br.com.movieapp.movie_favorite_feature.domain.source

import br.com.movieapp.core.domain.Movie
import kotlinx.coroutines.flow.Flow

interface MovieFavoriteLocalDataSource {

    fun getMovies(): Flow<List<Movie>>

    suspend fun insert(movie: Movie)
    suspend fun delete(movie: Movie)
    suspend fun isFavorite(movieId: Int): Boolean
}