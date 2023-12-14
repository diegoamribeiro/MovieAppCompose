package br.com.movieapp.movie_detail_feature.presentation

import br.com.movieapp.core.domain.Movie

sealed class MovieDetailEvent {
    data class GetMovieDetails(val movieId: Int) : MovieDetailEvent()
    data class AddFavorite(val movie: Movie) : MovieDetailEvent()
    data class CheckedFavorite(val movieId: Int) : MovieDetailEvent()
    data class RemoveFavorite(val movie: Movie) : MovieDetailEvent()
}