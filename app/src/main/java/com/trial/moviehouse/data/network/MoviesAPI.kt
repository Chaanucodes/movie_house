package com.trial.moviehouse.data.network

import com.trial.moviehouse.data.models.CustomResponse
import com.trial.moviehouse.data.models.Movie
import com.trial.moviehouse.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("upcoming")
    suspend fun getMovies(
        @Query("api_key")apiKey: String  = Constants.API_KEY
    ): Response<CustomResponse<List<Movie>>>
}