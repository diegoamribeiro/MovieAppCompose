package br.com.movieapp.movie_favorite_feature.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import br.com.movieapp.R
import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.presentation.components.common.MovieAppBar
import br.com.movieapp.movie_favorite_feature.presentation.component.MovieFavoriteContent
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

@Composable
fun MovieFavoriteScreen(
    movies: List<Movie>,
    navigateToDetailMovie: (Int) -> Unit
) {

    Scaffold(
        topBar = {
            MovieAppBar(
                title = R.string.favorite_movies,
                textColor = white,
                backgroundColor = black
            )
        },
        content = { paddingValues ->
            MovieFavoriteContent(
                paddingValues = paddingValues,
                movies = movies,
                onCLick = { movieId ->
                    navigateToDetailMovie(movieId)
                }
            )
        }
    )

}