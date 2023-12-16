package com.trial.moviehouse.data.models

import com.google.gson.annotations.SerializedName


data class CustomResponse<T : Any>(val page: Int,
                                   @SerializedName(value = "results")
                                   val movies: List<Movie>,
                                   @SerializedName("total_pages")
                                   val totalPages: Int,
                                   @SerializedName("total_results")
                                   val totalResults: Int)
data class CustomGenreResponse<T : Any>(@SerializedName("genres") val data: T)
