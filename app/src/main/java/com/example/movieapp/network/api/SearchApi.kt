package com.example.movieapp.network.api

import com.example.movieapp.models.movieoverview.MovieQueryResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("${prefix}/movie")
    suspend fun searchMovie(@Query("query") query: String, @Query("page") page: Long): Response<MovieQueryResult>

    companion object{
        const val prefix = "search"
    }

}