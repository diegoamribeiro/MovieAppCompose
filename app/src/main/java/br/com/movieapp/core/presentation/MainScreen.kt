package br.com.movieapp.core.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.movieapp.core.presentation.navigation.BottomNavigationBar
import br.com.movieapp.core.presentation.navigation.DetailScreenNav
import br.com.movieapp.core.presentation.navigation.NavigationGraph
import br.com.movieapp.core.presentation.navigation.currentRoute

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navHostController: NavHostController
) {
    Scaffold(
        bottomBar = {
            if (currentRoute(navHostController = navHostController) != DetailScreenNav.DetailScreen.route){
                BottomNavigationBar(navController = navHostController)
            }

        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavigationGraph(navController = navHostController)
            }
        }
    )
}

