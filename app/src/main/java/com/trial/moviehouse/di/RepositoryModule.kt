package com.trial.moviehouse.di

import com.trial.moviehouse.data.db.MoviesDao
import com.trial.moviehouse.data.network.MoviesAPI
import com.trial.moviehouse.data.repository.MoviesRepository
import com.trial.moviehouse.data.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(moviesAPI: MoviesAPI, moviesDao: MoviesDao): MoviesRepository =
        MoviesRepositoryImpl(moviesAPI, moviesDao)
}