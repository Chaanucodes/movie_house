package com.trial.moviehouse.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.trial.moviehouse.data.db.MoviesDao
import com.trial.moviehouse.data.db.MoviesDatabase
import com.trial.moviehouse.data.models.Movie
import com.trial.moviehouse.data.network.MoviesAPI
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(
    private val movieDb: MoviesDatabase,
    private val moviesDao: MoviesDao,
    private val moviesAPI: MoviesAPI
) : RemoteMediator<Int, Movie>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    lastItem.id
                }
            }

            val moviesFeed = moviesAPI.getMovies(
                page = loadKey,
            )

            movieDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    moviesDao.deleteAllMovies()
                }
                val movieEntities = moviesFeed.body()?.movies ?: emptyList()

                moviesDao.insertMovies(movieEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = moviesFeed.body()?.movies?.isEmpty() ?: true
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}

