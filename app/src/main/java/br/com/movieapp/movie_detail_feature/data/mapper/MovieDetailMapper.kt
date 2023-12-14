package br.com.movieapp.movie_detail_feature.data.mapper

import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.domain.MovieDetails

fun MovieDetails.toMovie(): Movie {
    return  Movie(
        id = id,
        title = title,
        imageUrl = backDropPathUrl.toString(),
        voteAverage = voteAverage
    )
}