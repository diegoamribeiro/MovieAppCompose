package br.com.movieapp.movie_detail_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.domain.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int) : MovieDetails
    suspend fun getMoviesSimilar(movieId: Int, pagingConfig: PagingConfig) : Flow<PagingData<Movie>>
}