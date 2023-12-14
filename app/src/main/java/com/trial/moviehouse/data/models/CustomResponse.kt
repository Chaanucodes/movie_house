package com.trial.moviehouse.data.models

import com.google.gson.annotations.SerializedName


data class CustomResponse<T : Any>(@SerializedName("results") val data: T)
data class CustomGenreResponse<T : Any>(@SerializedName("genres") val data: T)