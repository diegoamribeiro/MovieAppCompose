package br.com.movieapp.movie_detail_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.Movie
import br.com.movieapp.core.domain.MovieDetails
import br.com.movieapp.movie_detail_feature.domain.repository.MovieDetailsRepository
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val movieDetailsRemoteDataSource: MovieDetailsRemoteDataSource
) : MovieDetailsRepository{
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return movieDetailsRemoteDataSource.getMovieDetails(movieId)
    }

    override suspend fun getMoviesSimilar(
        movieId: Int,
        pagingConfig: PagingConfig
    ): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                movieDetailsRemoteDataSource.getSimilarMoviesPagingSource(movieId = movieId)
            }
        ).flow
    }
}