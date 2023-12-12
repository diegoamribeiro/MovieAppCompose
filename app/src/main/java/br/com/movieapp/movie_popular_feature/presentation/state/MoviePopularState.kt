package br.com.movieapp.movie_popular_feature.presentation.state

import androidx.paging.PagingData
import br.com.movieapp.core.domain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MoviePopularState(
    val movies: Flow<PagingData<Movie>> = emptyFlow()
)
