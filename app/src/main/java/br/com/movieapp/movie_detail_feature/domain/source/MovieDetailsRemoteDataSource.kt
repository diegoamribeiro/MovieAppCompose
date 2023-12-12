package br.com.movieapp.movie_detail_feature.domain.source

import br.com.movieapp.core.data.remote.response.MovieResponse
import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.domain.MovieDetails
import br.com.movieapp.core.paging.MovieSimilarPagingSource

interface MovieDetailsRemoteDataSource {
    suspend fun getMovieDetails(movieId: Int) : MovieDetails
    suspend fun getMoviesSimilar(page: Int, movieId: Int) : MovieResponse
    fun getSimilarMoviesPagingSource(movieId: Int) : MovieSimilarPagingSource
}