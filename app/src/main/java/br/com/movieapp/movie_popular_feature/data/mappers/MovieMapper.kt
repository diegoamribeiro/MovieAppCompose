package br.com.movieapp.movie_popular_feature.data.mappers

import br.com.movieapp.core.data.remote.model.MovieResult
import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.util.toPostUrl

fun List<MovieResult>.toMovie() = map { movieResult ->
    Movie(
        id = movieResult.id,
        title = movieResult.title,
        voteAverage = movieResult.voteAverage,
        imageUrl = movieResult.posterPath.toPostUrl()
    )
}