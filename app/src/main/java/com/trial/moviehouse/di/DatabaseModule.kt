package com.trial.moviehouse.di

import android.content.Context
import androidx.room.Room
import com.trial.moviehouse.data.db.MoviesDao
import com.trial.moviehouse.data.db.MoviesDatabase
import com.trial.moviehouse.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideMovieDao(movieDatabase: MoviesDatabase): MoviesDao {
        return movieDatabase.moviesDao()
    }
}