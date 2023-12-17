package com.trial.moviehouse.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.trial.moviehouse.data.models.Movie
import com.trial.moviehouse.util.Constants

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>) : List<Long>

    @Upsert
    fun upsertMovies(movie: List<Movie>) : List<Long>

    @Query("DELETE FROM ${Constants.DATABASE_NAME}")
    fun deleteAllMovies()

    @Query("SELECT * FROM ${Constants.DATABASE_NAME}")
    fun getPaginatedMovies() : PagingSource<Int, Movie>

}