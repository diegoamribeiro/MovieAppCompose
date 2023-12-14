package br.com.movieapp.movie_detail_feature.presentation.state

import androidx.compose.ui.graphics.Color
import androidx.paging.PagingData
import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.domain.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieDetailState(
    val movieDetails: MovieDetails? = null,
    val error: String = "",
    val isLoading: Boolean = false,
    val iconColor: Color = Color.White,
    val results: Flow<PagingData<Movie>> = emptyFlow()
)
