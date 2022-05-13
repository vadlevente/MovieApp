package com.example.movieapp.network

import com.example.movieapp.BuildConfig
import com.example.movieapp.network.api.MovieApi
import com.example.movieapp.network.interceptors.ApiKeyInterceptor
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface NetworkModule {
    fun getMovieApi(): MovieApi
}

class NetworkModuleImpl: NetworkModule{

    private val baseUrl = BuildConfig.API_URL
    private val gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()

    override fun getMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(MovieApi::class.java)
    }

    private fun createClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }

}