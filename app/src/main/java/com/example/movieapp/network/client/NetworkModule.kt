package com.example.movieapp.network

import com.example.movieapp.BuildConfig
import com.example.movieapp.network.api.ConfigurationApi
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
    fun getConfigurationApi(): ConfigurationApi
}

class NetworkModuleImpl: NetworkModule{

    private val baseUrl = BuildConfig.API_URL
    private val gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()

    private var client: OkHttpClient? = null

    override fun getMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(MovieApi::class.java)
    }

    override fun getConfigurationApi(): ConfigurationApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(ConfigurationApi::class.java)
    }

    private fun getClient(): OkHttpClient {
        if(client == null) {
            createClient()
        }
        return client!!
    }

    private fun createClient() {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }

}