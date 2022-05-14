package com.example.movieapp.network

import com.example.movieapp.BuildConfig
import com.example.movieapp.network.api.ConfigurationApi
import com.example.movieapp.network.api.MovieApi
import com.example.movieapp.network.api.SearchApi
import com.example.movieapp.network.interceptors.ApiKeyInterceptor
import com.example.movieapp.network.typeadapters.DateTypeAdapter
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


interface NetworkModule {
    fun getMovieApi(): MovieApi
    fun getConfigurationApi(): ConfigurationApi
    fun getSearchApi(): SearchApi
}

class NetworkModuleImpl: NetworkModule{

    private val baseUrl = BuildConfig.API_URL
    private val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateTypeAdapter())
        .create()

    private var client: OkHttpClient? = null

    override fun getMovieApi(): MovieApi {
        return getApi(MovieApi::class.java)
    }

    override fun getConfigurationApi(): ConfigurationApi {
        return getApi(ConfigurationApi::class.java)
    }

    override fun getSearchApi(): SearchApi {
        return getApi(SearchApi::class.java)
    }

    private fun <T> getApi(clazz: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(clazz)
    }

    private fun getClient(): OkHttpClient {
        if(client == null) {
            createClient()
        }
        return client!!
    }

    private fun createClient() {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }

}