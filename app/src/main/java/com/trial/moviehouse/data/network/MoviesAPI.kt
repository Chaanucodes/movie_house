package com.trial.moviehouse.data.network

import com.trial.moviehouse.data.models.CustomGenreResponse
import com.trial.moviehouse.data.models.MovieAPIResponse
import com.trial.moviehouse.data.models.Genre
import com.trial.moviehouse.data.models.Movie
import com.trial.moviehouse.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key")apiKey: String  = Constants.API_KEY,
        @Query("page") page: Int = 1
    ): Response<MovieAPIResponse<List<Movie>>>

    @GET("genre/movie/list")
    suspend fun getGenres() : Response<CustomGenreResponse<List<Genre>>>
}