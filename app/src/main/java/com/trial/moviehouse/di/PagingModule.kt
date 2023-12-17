package com.trial.moviehouse.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.trial.moviehouse.data.db.MoviesDao
import com.trial.moviehouse.data.db.MoviesDatabase
import com.trial.moviehouse.data.models.Movie
import com.trial.moviehouse.data.network.MoviesAPI
import com.trial.moviehouse.data.paging.MovieRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagingModule {
    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideMoviesPager(
        moviesDb: MoviesDatabase,
        moviesAPI: MoviesAPI,
        moviesDao: MoviesDao
    ): Pager<Int, Movie> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
            remoteMediator = MovieRemoteMediator(
                movieDb = moviesDb,
                moviesAPI = moviesAPI,
                moviesDao = moviesDao
            ),
            pagingSourceFactory = {
                moviesDao.getPaginatedMovies()
            }
        )
    }
}