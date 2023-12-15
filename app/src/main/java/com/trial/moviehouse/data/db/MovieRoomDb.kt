package com.trial.moviehouse.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.trial.moviehouse.data.models.GenreConverter
import com.trial.moviehouse.data.models.Movie


@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(GenreConverter::class)
abstract class MoviesDatabase : RoomDatabase() {
        abstract fun moviesDao(): MoviesDao
}