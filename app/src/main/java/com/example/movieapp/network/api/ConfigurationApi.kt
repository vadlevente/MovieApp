package com.example.movieapp.network.api

import com.example.movieapp.models.MovieConfiguration
import retrofit2.Response
import retrofit2.http.GET

interface ConfigurationApi {

    @GET(prefix)
    suspend fun getConfiguration(): Response<MovieConfiguration>

    companion object{
        const val prefix = "configuration"
    }

}