package br.com.movieapp.movie_favorite_feature.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.movieapp.core.domain.Movie
import br.com.movieapp.ui.theme.black

@Composable
fun MovieFavoriteContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    movies: List<Movie>,
    onCLick: (id: Int) -> Unit
) {

    Box(modifier = modifier.background(black)){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = paddingValues,
            content = {
                items(
                    items = movies,
                    key = { item: Movie ->
                        item.id
                    }
                ){ movie ->
                    MovieFavoriteItem(
                        movie = movie,
                        onClick = onCLick
                    )
                }
            })
    }

}