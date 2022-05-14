package com.example.movieapp.network.api

import com.example.movieapp.models.MovieQueryResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("${prefix}/movie")
    suspend fun searchMovie(@Query("query") query: String): Response<MovieQueryResult>

    companion object{
        const val prefix = "search"
    }

}