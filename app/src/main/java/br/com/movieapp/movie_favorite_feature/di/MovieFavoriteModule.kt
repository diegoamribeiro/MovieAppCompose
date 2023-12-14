package br.com.movieapp.movie_favorite_feature.di

import br.com.movieapp.core.data.local.dao.MovieDao
import br.com.movieapp.movie_favorite_feature.data.repository.MovieFavoriteRepositoryImpl
import br.com.movieapp.movie_favorite_feature.data.source.MovieFavoriteLocalDataSourceImpl
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import br.com.movieapp.movie_favorite_feature.domain.source.MovieFavoriteLocalDataSource
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MovieFavoriteModule {

    @Provides
    @Singleton
    fun provideMovieFavoriteLocalDataSource(dao: MovieDao) : MovieFavoriteLocalDataSource {
        return MovieFavoriteLocalDataSourceImpl(dao = dao)
    }

    @Provides
    @Singleton
    fun provideMovieFavoriteRepository(movieFavoriteLocalDataSource: MovieFavoriteLocalDataSource) : MovieFavoriteRepository {
        return MovieFavoriteRepositoryImpl(localDataSource = movieFavoriteLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteRepository(movieFavoriteRepository: MovieFavoriteRepository) : GetMoviesFavoriteUseCase {
        return GetMoviesFavoriteUseCaseImpl(movieFavoriteRepository = movieFavoriteRepository)
    }

    @Provides
    @Singleton
    fun provideAddMovieFavoriteUseCase(movieFavoriteRepository: MovieFavoriteRepository) : AddMovieFavoriteUseCase {
        return AddMovieFavoriteUseCaseImpl(movieFavoriteRepository = movieFavoriteRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteMovieFavoriteUseCase(movieFavoriteRepository: MovieFavoriteRepository) : DeleteMovieFavoriteUseCase {
        return DeleteMovieFavoriteUseCaseImpl(movieFavoriteRepository = movieFavoriteRepository)
    }
    @Provides
    @Singleton
    fun provideIsMovieFavoriteUseCase(movieFavoriteRepository: MovieFavoriteRepository) : IsMovieFavoriteUseCase{
        return IsMovieFavoriteUseCaseImpl(movieFavoriteRepository = movieFavoriteRepository)
    }
}