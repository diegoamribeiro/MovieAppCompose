package br.com.movieapp.search_movie_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import br.com.movieapp.search_movie_feature.domain.usecase.SearchMovieUseCase
import br.com.movieapp.search_movie_feature.presentation.state.MovieSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {
    var uiState by mutableStateOf(MovieSearchState())
        private set

    fun fetch(query: String = ""){
        val movies = searchMovieUseCase.invoke(
            params = SearchMovieUseCase.Params(
                query = query
            )
        ).cachedIn(viewModelScope)
        uiState = uiState.copy(movies = movies)
    }

    fun event(event: MovieSearchEvent) {
        uiState = when(event){
            is MovieSearchEvent.EnteredQuery -> {
                uiState.copy(query = event.value)
            }
        }
    }
}