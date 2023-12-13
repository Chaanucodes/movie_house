package com.trial.moviehouse.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trial.moviehouse.data.models.Movie
import com.trial.moviehouse.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>) : List<Long>

    @Query("SELECT * FROM ${Constants.DATABASE_NAME}")
    fun getMovies() : Flow<List<Movie>>

}