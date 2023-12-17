package com.trial.moviehouse.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.trial.moviehouse.data.db.MoviesDao
import com.trial.moviehouse.data.db.MoviesDatabase
import com.trial.moviehouse.data.models.Movie
import com.trial.moviehouse.data.models.MovieAPIResponse
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

    companion object{
        private var currentPage = 1
    }
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {
        return try {
            var moviesFeed : MovieAPIResponse<List<Movie>>? = null
            when (loadType) {
                LoadType.REFRESH -> {
                    currentPage = 1
                    currentPage
                }
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    moviesFeed = moviesAPI.getMovies(
                        page = currentPage,
                    ).body()

                    if(moviesFeed!=null && moviesFeed.movies.isNotEmpty()){
                        if(moviesFeed.page<moviesFeed.totalPages){
                            currentPage = moviesFeed.page + 1
                            currentPage
                        }else{
                            return MediatorResult.Success(endOfPaginationReached = true)
                        }
                    }else{
                        return MediatorResult.Success(endOfPaginationReached = true)

                    }
                }
            }

            movieDb.withTransaction {
                val movieEntities = moviesFeed?.movies?: emptyList()

                if (loadType == LoadType.REFRESH || (movieEntities.isNotEmpty() && currentPage == 1)) {
                    moviesDao.deleteAllMovies()
                }

                if(movieEntities.isEmpty()){
                    return@withTransaction MediatorResult.Success(endOfPaginationReached = true)
                }else{
                    moviesDao.upsertMovies(movieEntities)

                }
            }

            MediatorResult.Success(
                endOfPaginationReached = moviesFeed?.movies?.isEmpty() ?: true
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}

