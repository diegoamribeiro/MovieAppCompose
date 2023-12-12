package br.com.movieapp.movie_detail_feature.di

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.movie_detail_feature.data.repository.MovieDetailsRepositoryImpl
import br.com.movieapp.movie_detail_feature.data.source.MovieDetailsRemoteDataSourceImpl
import br.com.movieapp.movie_detail_feature.domain.repository.MovieDetailsRepository
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsModule {

    @Provides
    @Singleton
    fun provideMovieDetailsDataSource(service: MovieService) : MovieDetailsRemoteDataSource {
        return MovieDetailsRemoteDataSourceImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideMovieDetailRepository(movieDetailsRemoteDataSource: MovieDetailsRemoteDataSource) : MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(movieDetailsRemoteDataSource = movieDetailsRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(repository: MovieDetailsRepository) : GetMovieDetailsUseCase {
        return GetMovieDetailsUseCaseImpl(repository = repository)
    }
}