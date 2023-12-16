package br.com.movieapp.core.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import br.com.movieapp.core.util.Constants

sealed class DetailScreenNav(
    val route: String
) {
    object DetailScreen: DetailScreenNav(
        route = "movie_detail_destination?${Constants.MOVIE_DETAIL_ARGUMENT_KEY}={${Constants.MOVIE_DETAIL_ARGUMENT_KEY}}"
    ){
        fun passMovieId(movieId: Int) = "movie_detail_destination?${Constants.MOVIE_DETAIL_ARGUMENT_KEY}=$movieId"
    }
}