package br.com.movieapp.movie_detail_feature.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.MovieDetailsFactory
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.core.util.ResourceData
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import br.com.movieapp.movie_popular_feature.TestDispatchersRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.lang.Exception


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest{

    @get:Rule
    val dispatcherRule = TestDispatchersRule()

    @Mock
    lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    @Mock
    lateinit var addMovieFavoriteUseCase: AddMovieFavoriteUseCase
    @Mock
    lateinit var deleteMovieUseCase: DeleteMovieFavoriteUseCase
    @Mock
    lateinit var isMovieFavoriteUseCase: IsMovieFavoriteUseCase
    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    private val movieDetailsFactory = MovieDetailsFactory().create(poster = MovieDetailsFactory.Poster.Avengers)
    private val pagingData = PagingData.from(
        listOf(
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
            MovieFactory().create(poster = MovieFactory.Poster.JohnWick)
        )
    )
    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)
    private val viewModel by lazy {
        MovieDetailViewModel(
            getMovieDetailUseCase = getMovieDetailsUseCase,
            addMovieFavoriteUseCase = addMovieFavoriteUseCase,
            deleteMovieFavoriteUseCase = deleteMovieUseCase,
            isMovieFavoriteUseCase = isMovieFavoriteUseCase,
            savedStateHandle = savedStateHandle.apply {
                whenever(savedStateHandle.get<Int>("movieId")).thenReturn(movie.id)
            }
        )
    }


    @Test
    fun `must notify uiState with success when get movies similar and movie details returns success`() = runTest {
        // Given
        whenever(getMovieDetailsUseCase.invoke(any())).thenReturn(
            flowOf(ResourceData.Success(flowOf(pagingData) to movieDetailsFactory))
        )
        val argumentCaptor = argumentCaptor<GetMovieDetailsUseCase.Params>()
        // When
        viewModel.uiState.isLoading
        // Then
        verify(getMovieDetailsUseCase).invoke(argumentCaptor.capture())
        assertThat(movieDetailsFactory.id).isEqualTo(argumentCaptor.firstValue.movieId)
        val movieDetails = viewModel.uiState.movieDetails
        val results = viewModel.uiState.results
        assertThat(movieDetails).isNotNull()
        assertThat(results).isNotNull()
    }


    @Test
    fun `must notify uiState favorite when get movies details and returns exception`() = runTest {
        // Given
        val exception = Exception("Um erro ocorreu")
        whenever(getMovieDetailsUseCase.invoke(any())).thenReturn(
            flowOf(ResourceData.Fail(exception))
        )
        // When
        viewModel.uiState.isLoading
        // Then
        val error = viewModel.uiState.error
        assertThat(exception.message).isEqualTo(error)
    }

    @Test
    fun `must call delete favorite and notify of uiState with filled favorite icon when current icon checked`() = runTest {
        // Given
        whenever(deleteMovieUseCase.invoke(any())).thenReturn(
            flowOf(ResourceData.Success(Unit))
        )
        whenever(isMovieFavoriteUseCase.invoke(any())).thenReturn(
            flowOf(ResourceData.Success(true))
        )
        val deleteArgumentCaptor = argumentCaptor<DeleteMovieFavoriteUseCase.Params>()
        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()
        // When
        viewModel.onAddFavorite(movie = movie)
        // Then
        verify(deleteMovieUseCase).invoke(deleteArgumentCaptor.capture())
        assertThat(movie).isEqualTo(deleteArgumentCaptor.firstValue.movie)

        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.White).isEqualTo(iconColor)
    }

    @Test
    fun `must notify uiState with filled favorite icon when current icon is unchecked`() = runTest {
        // Given
        whenever(addMovieFavoriteUseCase.invoke(any())).thenReturn(
            flowOf(ResourceData.Success(Unit))
        )
        whenever(isMovieFavoriteUseCase.invoke(any())).thenReturn(
            flowOf(ResourceData.Success(false))
        )
        val addArgumentCaptor = argumentCaptor<AddMovieFavoriteUseCase.Params>()
        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()
        // When
        viewModel.onAddFavorite(movie = movie)
        // Then
        verify(addMovieFavoriteUseCase).invoke(addArgumentCaptor.capture())
        assertThat(movie).isEqualTo(addArgumentCaptor.firstValue.movie)

        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.Red).isEqualTo(iconColor)
    }

    @Test
    fun `must notify uiState with bookmark icon filled in if bookmark returns true`() = runTest {
        // Given
        whenever(isMovieFavoriteUseCase.invoke(any())).thenReturn(
            flowOf(ResourceData.Success(true))
        )
        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()
        // When
        viewModel.uiState.isLoading

        // Then
        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.Red).isEqualTo(iconColor)
    }

    @Test
    fun `must notify uiState with bookmark icon filled in if bookmark returns false`() = runTest {
        // Given
        whenever(isMovieFavoriteUseCase.invoke(any())).thenReturn(
            flowOf(ResourceData.Success(false))
        )
        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()
        // When
        viewModel.uiState.isLoading

        // Then
        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.White).isEqualTo(iconColor)
    }
}