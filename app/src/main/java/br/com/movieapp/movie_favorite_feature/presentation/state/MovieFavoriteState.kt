package br.com.movieapp.movie_favorite_feature.presentation.state

import br.com.movieapp.core.domain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieFavoriteState(
    val movies: Flow<List<Movie>> = emptyFlow()
)
