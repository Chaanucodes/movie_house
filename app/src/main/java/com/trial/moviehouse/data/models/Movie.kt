package com.trial.moviehouse.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trial.moviehouse.util.Constants

@Entity(tableName = Constants.DATABASE_NAME)
@TypeConverters(
    GenreConverter::class,
    ProductionCompanyConverter::class,
    ProductionCountryConverter::class,
    SpokenLanguageConverter::class
)
data class Movie(
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val belongsToCollection: Boolean?,//To skip
    val budget: Int?,
    val genres: List<Genre?>?,//To Skip
    val homepage: String?,
    @PrimaryKey
    val id: Int,
    val imdbId: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val productionCompanies: List<ProductionCompany?>?,//To Skip
    val productionCountries: List<ProductionCountry?>?,//To Skip
    @SerializedName("release_date")
    val releaseDate: String?,
    val revenue: Long?,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguage?>?,//To Skip
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String
)

data class ProductionCountry(
    val iso31661: String,
    val name: String
)

data class SpokenLanguage(
    val englishName: String,
    val iso6391: String,
    val name: String
)

class GenreConverter {
    @TypeConverter
    fun fromString(value: String): List<Genre>? {
        val listType = object : TypeToken<List<Genre>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Genre>?): String {
        return Gson().toJson(list)
    }
}

class ProductionCompanyConverter {
    @TypeConverter
    fun fromString(value: String): List<ProductionCompany>? {
        val listType = object : TypeToken<List<ProductionCompany>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<ProductionCompany>?): String {
        return Gson().toJson(list)
    }
}

class ProductionCountryConverter {
    @TypeConverter
    fun fromString(value: String): List<ProductionCountry>? {
        val listType = object : TypeToken<List<ProductionCountry>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<ProductionCountry>?): String {
        return Gson().toJson(list)
    }
}

class SpokenLanguageConverter {
    @TypeConverter
    fun fromString(value: String): List<SpokenLanguage>? {
        val listType = object : TypeToken<List<SpokenLanguage>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<SpokenLanguage>?): String {
        return Gson().toJson(list)
    }
}


