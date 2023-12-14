package br.com.movieapp.movie_detail_feature.presentation.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.domain.MovieDetails
import br.com.movieapp.movie_detail_feature.data.mapper.toMovie
import br.com.movieapp.ui.theme.white
import br.com.movieapp.ui.theme.yellow
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import kotlinx.coroutines.flow.flowOf

@Composable
fun MovieDetailContent(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetails?,
    pagingMoviesSimilar: LazyPagingItems<Movie>,
    isLoading: Boolean,
    isError: String,
    iconColor: Color,
    onAddFavorite: (Movie) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6F)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MovieDetailsBackDropImage(
                backDropImageUrl = movieDetails?.backDropPathUrl.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        movieDetails?.toMovie()?.let { movie ->
                            onAddFavorite(movie)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = iconColor
                    )
                }
            }
            Text(
                text = movieDetails?.title.toString(),
                color = white,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                mainAxisSpacing = 10.dp,
                mainAxisAlignment = MainAxisAlignment.Center,
                crossAxisSpacing = 10.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                movieDetails?.genres?.forEach { genre ->
                    GenreTag(genre = genre)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            MovieInfoContent(
                movieDetails = movieDetails,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            RatingBar(
                rating = (movieDetails?.voteAverage?.toFloat()?.div(2F)) ?: 0f,
                modifier = Modifier.height(15.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            MovieDetailOverview(
                overView = movieDetails?.overview.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(id = R.string.movies_similar),
                color = white,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(horizontal = 8.dp)
            )
        }
        if (isError.isNotEmpty()) {
            Text(
                text = isError,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .align(Alignment.TopCenter)
            )
        }
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                color = yellow
            )
        }
        MovieDetailsSimilarMovies(
            pagingMoviesSimilar = pagingMoviesSimilar,
            modifier = Modifier
                .fillMaxHeight(0.35f)
                .align(Alignment.BottomEnd)
        )
    }
}

@Preview
@Composable
fun MovieDetailsContentPreview() {
    MovieDetailContent(
        movieDetails = MovieDetails(
            id = 1,
            title = "Homem Aranha",
            genres = listOf("Ação", "Aventura", "Aventura", "Aventura", "Aventura", "Aventura"),
            overview = "Depois do nosso herói ter sido desmascarado pelo seu inimigo Mysterio, Peter Parker já não é capaz de separar a sua vida normal do seu estatuto de super-herói, pelo que só lhe resta pedir ajuda ao Mestre das Artes Místicas, o Doutor Estranho, para que apague a sua real identidade da cabeça de todos. No entanto, esse feitiço leva-o a uma realidade que não é a sua e onde terá de enfrentar novos inimigos, ainda mais perigosos, forçando-o a descobrir o que realmente significa ser o Homem-Aranha.",
            backDropPathUrl = "",
            releaseDate = "25/05/2022",
            voteAverage = 1.4
        ),
// criar um fluxo (flow) a partir de um ou mais elementos.
        pagingMoviesSimilar = flowOf(PagingData.from(emptyList<Movie>())).collectAsLazyPagingItems(),
        isError = "Error",
        isLoading = false,
        iconColor = Color.Red,
        onAddFavorite = {}
    )
}