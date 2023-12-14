package br.com.movieapp.core.di

import android.content.Context
import androidx.room.Room
import br.com.movieapp.core.data.local.MovieDatabase
import br.com.movieapp.core.data.local.dao.MovieDao
import br.com.movieapp.core.util.Constants.MOVIE_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {


    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            MOVIE_DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase) : MovieDao {
        return database.movieDao()
    }
}