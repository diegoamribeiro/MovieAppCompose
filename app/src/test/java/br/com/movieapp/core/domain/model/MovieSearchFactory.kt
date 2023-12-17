package br.com.movieapp.core.domain.model

import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.domain.MovieSearch

class MovieSearchFactory {

    fun create(
        poster: Poster
    ) = when(poster){
        Poster.Avengers -> {
            MovieSearch(
                id = 1,
                voteAverage = 7.1,
                imageUrl = "Url"
            )
        }
        Poster.JohnWick -> {
            MovieSearch(
                id = 2,
                voteAverage = 7.9,
                imageUrl = "Url"
            )
        }
    }

    sealed class Poster{
        object Avengers: Poster()
        object JohnWick: Poster()
    }
}