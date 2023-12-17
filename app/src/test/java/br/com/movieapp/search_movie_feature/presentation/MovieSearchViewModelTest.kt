package br.com.movieapp.search_movie_feature.presentation

import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.core.domain.model.MovieSearchFactory
import br.com.movieapp.movie_popular_feature.TestDispatchersRule
import br.com.movieapp.search_movie_feature.domain.usecase.SearchMovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieSearchViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatchersRule()

    @Mock
    lateinit var searchMovieUseCase: SearchMovieUseCase

    @Mock
    lateinit var viewModel: MovieSearchViewModel

    @Before
    fun setup() {
        viewModel = MovieSearchViewModel(searchMovieUseCase)
    }

    private val searchFakePagingDataMovies = PagingData.from(
        listOf(
            MovieSearchFactory().create(poster = MovieSearchFactory.Poster.Avengers),
            MovieSearchFactory().create(poster = MovieSearchFactory.Poster.JohnWick)
        )
    )

    @Test
    fun `must validate paging data object values when calling search paging data`() = runTest {
        // Given
        whenever(searchMovieUseCase.invoke(any())).thenReturn(
            flowOf(searchFakePagingDataMovies)
        )
        // When
        viewModel.fetch("")
        val result = viewModel.uiState.movies.first()

        // Then
        assertThat(result).isNotNull()
    }

    @Test(expected = RuntimeException::class)
    fun `must throw an exception when the calling to the use case returns an exception`() = runTest {
        // Given
        whenever(searchMovieUseCase.invoke(any())).thenThrow(
            RuntimeException()
        )
        // When
        viewModel.fetch()
    }

}