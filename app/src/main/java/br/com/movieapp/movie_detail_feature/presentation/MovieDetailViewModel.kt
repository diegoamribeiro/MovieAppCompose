package br.com.movieapp.movie_detail_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.util.ResourceData
import br.com.movieapp.core.util.UtilsFunctions
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.movie_detail_feature.presentation.state.MovieDetailState
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: GetMovieDetailsUseCase,
    private val addMovieFavoriteUseCase: AddMovieFavoriteUseCase,
    private val deleteMovieFavoriteUseCase: DeleteMovieFavoriteUseCase,
    private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase
) : ViewModel() {
    var uiState by mutableStateOf(MovieDetailState())
        private set

    fun checkedFavorite(checkedFavorite: MovieDetailEvent.CheckedFavorite){
        event(checkedFavorite)
    }

    fun getMovieDetail(getMovieDetail: MovieDetailEvent.GetMovieDetails){
        event(getMovieDetail)
    }

    fun onAddFavorite(movie: Movie){
        if (uiState.iconColor == Color.White){
            event(MovieDetailEvent.AddFavorite(movie = movie))
        }else{
            event(MovieDetailEvent.RemoveFavorite(movie = movie))
        }
    }

    private fun event(event: MovieDetailEvent) {
        when(event){

            is MovieDetailEvent.AddFavorite -> {
                viewModelScope.launch {
                    addMovieFavoriteUseCase.invoke(params = AddMovieFavoriteUseCase.Params(
                        movie = event.movie
                    )).collectLatest { result ->
                        when(result){
                            is ResourceData.Success -> {
                                uiState = uiState.copy(iconColor = Color.Red)
                            }
                            is ResourceData.Fail -> {
                                UtilsFunctions.logError("DETAIL", "Erro ao cadastrar filme")
                            }
                            is ResourceData.Loading -> {}
                        }
                    }
                }
            }
            is MovieDetailEvent.CheckedFavorite -> {
                viewModelScope.launch {
                    isMovieFavoriteUseCase.invoke(params = IsMovieFavoriteUseCase.Params(
                        movieId = event.movieId
                    )).collectLatest { result ->
                        when (result){
                            is ResourceData.Success -> {
                                uiState = if (result.data == true){
                                    uiState.copy(iconColor = Color.Red)
                                }else{
                                    uiState.copy(iconColor = Color.White)
                                }
                            }
                            is ResourceData.Fail -> {
                                UtilsFunctions.logError("DETAIL", "Um erro occoreu")
                            }
                            is ResourceData.Loading -> {

                            }
                        }
                    }
                }
            }
            is MovieDetailEvent.RemoveFavorite -> {
                viewModelScope.launch {
                    deleteMovieFavoriteUseCase.invoke(DeleteMovieFavoriteUseCase.Params(
                        movie = event.movie
                    )).collectLatest { result ->
                        when(result){
                            is ResourceData.Success -> {
                                uiState = uiState.copy(iconColor = Color.White)
                            }
                            is ResourceData.Fail -> {
                                UtilsFunctions.logError("DETAIL", "Um erro occoreu")
                            }
                            is ResourceData.Loading -> {

                            }
                        }
                    }
                }
            }


            is MovieDetailEvent.GetMovieDetails -> {
                viewModelScope.launch {
                    movieDetailUseCase.invoke(
                        params = GetMovieDetailsUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collect{ resultData ->
                        when(resultData){
                            is ResourceData.Success -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    movieDetails = resultData.data.second,
                                    results = resultData.data?.first ?: emptyFlow()
                                )
                            }
                            is ResourceData.Fail -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    error = resultData.e?.message.toString()
                                )
                                UtilsFunctions.logError(
                                    "DETAIL_ERROR",
                                    resultData.e?.message.toString()
                                )
                            }
                            is ResourceData.Loading -> {
                                uiState = uiState.copy(
                                    isLoading = true
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}