package br.com.movieapp.movie_favorite_feature.presentation

import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import br.com.movieapp.movie_popular_feature.TestDispatchersRule
import br.com.movieapp.search_movie_feature.presentation.MovieSearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieFavoriteViewModelTest {


    @get:Rule
    val dispatcherRule = TestDispatchersRule()

    @Mock
    lateinit var getMoviesFavoriteUseCase: GetMoviesFavoriteUseCase

    private val viewModel by lazy { MovieFavoriteViewModel(getMoviesFavoriteUseCase) }

    private val fakeFavorites = listOf(
        MovieFactory().create(poster = MovieFactory.Poster.Avengers),
        MovieFactory().create(poster = MovieFactory.Poster.JohnWick)
    )

    @Test
    fun `must validate the data object values when calling list of favorites`() = runTest {
        // Given
        whenever(getMoviesFavoriteUseCase.invoke()).thenReturn(
            flowOf(fakeFavorites)
        )

        // When
        val result = viewModel.uiState.movies.first()

        // Then
        assertThat(result).isNotEmpty()
        assertThat(result).contains(fakeFavorites[0])
    }
}