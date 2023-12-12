package br.com.movieapp.search_movie_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.MovieSearch
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {

    fun searchMovies(
        query: String,
        pagingConfig: PagingConfig
    ) : Flow<PagingData<MovieSearch>>
}