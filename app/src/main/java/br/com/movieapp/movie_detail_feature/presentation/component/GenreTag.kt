package br.com.movieapp.movie_detail_feature.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.movieapp.ui.theme.white

@Composable
fun GenreTag(
    genre: String
) {
    Box(modifier = Modifier.border(
        width = 1.dp,
        color = white,
        shape = RoundedCornerShape(100.dp))
        .padding(10.dp)){
        Text(
            text = genre,
            color = white,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
            )
    }
}

@Preview(showBackground = true, backgroundColor = 0)
@Composable
fun GenreTagPreview(){
    GenreTag(genre = "Aventura")
}