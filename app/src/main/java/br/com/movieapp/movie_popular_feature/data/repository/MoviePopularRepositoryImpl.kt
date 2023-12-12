package br.com.movieapp.movie_popular_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.Movie
import br.com.movieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import br.com.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviePopularRepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviePopularRemoteDataSource
) : MoviePopularRepository {
    override fun getPopularMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getPopularMoviesPagingSource()
            }
        ).flow
    }
}