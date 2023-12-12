package br.com.movieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.movieapp.core.domain.MovieSearch
import br.com.movieapp.search_movie_feature.data.mapper.toMovieSearch
import br.com.movieapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import retrofit2.HttpException
import java.io.IOException

class MovieSearchPagingSource(
    private val query: String,
    private val remoteDataSource: MovieSearchRemoteDataSource
) : PagingSource<Int, MovieSearch>() {
    override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(
                LIMIT
            )
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteDataSource.searchMovies(page = pageNumber, query = query)
            val movies = response.searchResults
            LoadResult.Page(
                data = movies.toMovieSearch(),
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (movies.isEmpty()) null else pageNumber + 1
            )
        }catch (exception: IOException){
            exception.printStackTrace()
            return LoadResult.Error(exception)
        }catch (exception: HttpException){
            exception.printStackTrace()
            return LoadResult.Error(exception)
        }
    }

    companion object{
        private const val LIMIT = 20
    }

}
