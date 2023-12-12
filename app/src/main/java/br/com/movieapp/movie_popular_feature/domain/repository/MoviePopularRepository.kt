package br.com.movieapp.movie_popular_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.Movie
import kotlinx.coroutines.flow.Flow

interface MoviePopularRepository {
    fun getPopularMovies(pagingConfig: PagingConfig) : Flow<PagingData<Movie>>
}