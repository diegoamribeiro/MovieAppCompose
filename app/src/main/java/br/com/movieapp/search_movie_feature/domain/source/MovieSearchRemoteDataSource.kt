package br.com.movieapp.search_movie_feature.domain.source

import br.com.movieapp.core.data.remote.response.SearchResponse
import br.com.movieapp.core.paging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {
    fun searchMoviePagingSource(
        query: String
    ) : MovieSearchPagingSource

    suspend fun searchMovies(
        page: Int,
        query: String) : SearchResponse
}