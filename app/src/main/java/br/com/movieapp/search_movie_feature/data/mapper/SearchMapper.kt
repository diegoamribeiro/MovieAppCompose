package br.com.movieapp.search_movie_feature.data.mapper

import br.com.movieapp.core.data.remote.model.SearchResult
import br.com.movieapp.core.domain.MovieSearch
import br.com.movieapp.core.util.toPostUrl

fun List<SearchResult>.toMovieSearch() = map { searchResult ->
    MovieSearch(
        id = searchResult.id,
        imageUrl = searchResult.posterPath.toPostUrl(),
        voteAverage = searchResult.voteAverage
    )
}