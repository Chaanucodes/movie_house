package com.trial.moviehouse.data.models

import android.os.Parcel
import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trial.moviehouse.util.Constants
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.Parceler

@Entity(tableName = Constants.DATABASE_NAME)
@Parcelize
@TypeConverters(GenreConverter::class)
data class Movie(
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    val genres: List<Int?>?,
    @SerializedName("id")
    val id: Int,
    @PrimaryKey(autoGenerate = true)
    val movieId: Int = 0,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
) : Parcelable {


    override fun describeContents(): Int { return 0 }

    companion object : Parceler<Movie> {
        override fun Movie.write(parcel: Parcel, flags: Int) {}
        override fun create(parcel: Parcel): Movie = TODO()

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.movieId == newItem.movieId
            }
        }
    }
}

class GenreConverter {
    @TypeConverter
    fun fromString(value: String): List<Int>? {
        val listType = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Int>?): String {
        return Gson().toJson(list)
    }
}



