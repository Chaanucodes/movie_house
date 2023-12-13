package com.trial.moviehouse.di

import com.trial.moviehouse.data.network.MoviesAPI
import com.trial.moviehouse.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideRetrofit(): MoviesAPI {
        val retrofit =  Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService : MoviesAPI by lazy {
            retrofit.create(MoviesAPI::class.java)
        }

        return retrofitService
    }




}