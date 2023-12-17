package br.com.movieapp.core.domain.model

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.domain.MovieDetails
import br.com.movieapp.core.domain.MovieSearch

class MovieDetailsFactory {

    fun create(
        poster: Poster
    ) = when(poster){
        Poster.Avengers -> {
            MovieDetails(
                id = 1,
                title = "Avengers",
                voteAverage = 7.1,
                backDropPathUrl = "Url",
                genres = listOf("Aventura", "Ficção", "Ação"),
                overview = LoremIpsum(100).values.first(),
                releaseDate = "04/05/2012",
                duration = 143,
                voteCount = 7
            )
        }
        Poster.JohnWick -> {
            MovieDetails(
                id = 1,
                title = "John Wick",
                voteAverage = 7.9,
                backDropPathUrl = "Url",
                genres = listOf("Ação", "Thriller", "Crime"),
                overview = LoremIpsum(100).values.first(),
                releaseDate = "22/03/2023",
                duration = 169,
                voteCount = 7
            )
        }
    }

    sealed class Poster{
        object Avengers: Poster()
        object JohnWick: Poster()
    }
}