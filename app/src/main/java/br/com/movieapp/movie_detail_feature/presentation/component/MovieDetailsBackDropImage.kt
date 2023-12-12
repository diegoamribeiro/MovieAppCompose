package br.com.movieapp.movie_detail_feature.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.movieapp.R
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun MovieDetailsBackDropImage(
    backDropImageUrl: String,
    modifier: Modifier
) {
    Box(modifier = modifier){
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(backDropImageUrl)
                .crossfade(true)
                .error(R.drawable.ic_error_image)
                .placeholder(R.drawable.ic_placeholder)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun MovieDetailsBackDropImagePreview() {
    MovieDetailsBackDropImage(backDropImageUrl = "", modifier = Modifier.fillMaxWidth().height(200.dp))
}