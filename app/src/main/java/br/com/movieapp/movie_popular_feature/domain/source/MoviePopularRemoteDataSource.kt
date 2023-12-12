package br.com.movieapp.movie_popular_feature.domain.source

import br.com.movieapp.core.data.remote.response.MovieResponse
import br.com.movieapp.core.paging.MoviePagingSource

interface MoviePopularRemoteDataSource {

    suspend fun getPopularMovies(page: Int) : MovieResponse
    fun getPopularMoviesPagingSource() : MoviePagingSource
}