package br.com.movieapp.search_movie_feature.presentation

sealed class MovieSearchEvent {
    data class EnteredQuery(val value: String) : MovieSearchEvent()
}