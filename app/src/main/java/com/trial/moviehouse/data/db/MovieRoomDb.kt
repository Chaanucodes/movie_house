package com.trial.moviehouse.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.trial.moviehouse.data.models.GenreConverter
import com.trial.moviehouse.data.models.Movie
import com.trial.moviehouse.data.models.ProductionCompanyConverter
import com.trial.moviehouse.data.models.ProductionCountryConverter
import com.trial.moviehouse.data.models.SpokenLanguageConverter


@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(
        GenreConverter::class,
        ProductionCompanyConverter::class,
        ProductionCountryConverter::class,
        SpokenLanguageConverter::class
)
abstract class MoviesDatabase : RoomDatabase() {
        abstract fun moviesDao(): MoviesDao
}