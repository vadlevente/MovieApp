package com.example.movieapp.network.api

import com.example.movieapp.models.MovieDetails
import com.example.movieapp.models.MovieQueryResult
import retrofit2.Response
import retrofit2.http.*

interface MovieApi {

    @GET("${prefix}/popular")
    suspend fun getPopularMovies(@Query("page") page: Long): Response<MovieQueryResult>

    @GET("${prefix}/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Long): Response<MovieDetails>

    companion object{
        const val prefix = "movie"
    }

}