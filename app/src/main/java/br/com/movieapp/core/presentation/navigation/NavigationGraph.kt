package br.com.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.movieapp.core.util.UtilsFunctions
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularScreen
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularViewModel
import br.com.movieapp.search_movie_feature.presentation.MovieSearchEvent
import br.com.movieapp.search_movie_feature.presentation.MovieSearchScreen
import br.com.movieapp.search_movie_feature.presentation.MovieSearchViewModel


@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route
        ){
        composable(BottomNavItem.MoviePopular.route){

            val viewModel: MoviePopularViewModel = hiltViewModel()
            MoviePopularScreen(
                uiState = viewModel.uiState,
                navigateToDetailMovie = {}
            )
        }
        composable(BottomNavItem.MovieSearch.route){
            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onEvent: (MovieSearchEvent) -> Unit = viewModel::event
            val onFetch: (String) -> Unit = viewModel::fetch
            MovieSearchScreen(
                onEvent = onEvent,
                onFetch = onFetch,
                uiState = uiState,
                navigateToDetail = {

                }
            )
        }
        composable(BottomNavItem.MovieFavorite.route){

        }
    }

}