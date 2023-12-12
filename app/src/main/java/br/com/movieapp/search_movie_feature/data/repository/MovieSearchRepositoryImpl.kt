package br.com.movieapp.search_movie_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.MovieSearch
import br.com.movieapp.search_movie_feature.domain.repository.MovieSearchRepository
import br.com.movieapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieSearchRemoteDataSource
) : MovieSearchRepository{
    override fun searchMovies(
        query: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<MovieSearch>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.searchMoviePagingSource(query = query)
            }
        ).flow
    }
}