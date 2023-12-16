package com.trial.moviehouse.data.repository

import android.util.Log
import com.trial.moviehouse.data.db.MoviesDao
import com.trial.moviehouse.data.models.Movie
import com.trial.moviehouse.data.network.MoviesAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject



interface MoviesRepository {
    suspend fun getMovies() : Flow<Any>?

    suspend fun getNetworkMovies() : List<Movie>
}
class MoviesRepositoryImpl @Inject constructor(
    private val moviesAPI: MoviesAPI,
    private val moviesDao: MoviesDao
) : MoviesRepository {
    override suspend fun getMovies() = flow {
        try {
            val networkMovies = moviesAPI.getMovies()
            val networkMoviesList = networkMovies.body()?.movies
            if(networkMoviesList!= null){
                moviesDao.insertMovies(networkMoviesList)
                emit(networkMoviesList)
            }
        } catch (e: Exception) {
/*            val movies = moviesDao.getMovies()
            emitAll(movies)*/
            Log.e("MoviesRepository", "getMovies: ${e.message}")
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getNetworkMovies(): List<Movie> {
        val networkMovies = moviesAPI.getMovies()
        return networkMovies.body()?.movies?: emptyList()
    }
}







