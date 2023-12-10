package br.com.movieapp.framework.domain

data class MovieDetails(
    val id: Int,
    val title: String,
    val genres: List<String>,
    val overview: String?,
    val backDropPathUrl: String?,
    val releaseDate: String?,
    val duration: Int = 0,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
)
