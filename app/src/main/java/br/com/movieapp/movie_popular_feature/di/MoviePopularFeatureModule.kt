package br.com.movieapp.movie_popular_feature.di

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.movie_popular_feature.data.repository.MoviePopularRepositoryImpl
import br.com.movieapp.movie_popular_feature.data.source.MoviePopularRemoteDataSourceImpl
import br.com.movieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import br.com.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import br.com.movieapp.movie_popular_feature.domain.usecase.GetPopularMovieUseCase
import br.com.movieapp.movie_popular_feature.domain.usecase.GetPopularMovieUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MoviePopularFeatureModule {

    @Provides
    @Singleton
    fun provideMovieDataSource(service: MovieService) : MoviePopularRemoteDataSource{
        return  MoviePopularRemoteDataSourceImpl(service)
    }


    @Provides
    @Singleton
    fun provideMovieRepository(remoteDataSource: MoviePopularRemoteDataSource) : MoviePopularRepository {
        return MoviePopularRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMoviesPopularUseCase(moviePopularRepository: MoviePopularRepository) : GetPopularMovieUseCase {
        return GetPopularMovieUseCaseImpl(moviePopularRepository)
    }
}