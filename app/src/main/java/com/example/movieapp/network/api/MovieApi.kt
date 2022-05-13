package com.example.movieapp.network.api

import com.example.movieapp.models.MovieDetails
import com.example.movieapp.models.popularmovies.PopularMovies
import com.example.movieapp.network.model.requests.RateMovieRequest
import com.example.movieapp.network.model.response.PostResponse
import retrofit2.Response
import retrofit2.http.*

interface MovieApi {

    @GET("popular")
    suspend fun getPopularMovies(@Query("page") page: Long): Response<PopularMovies>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Long): Response<MovieDetails>

    @POST("movie/{movieId}/rating")
    suspend fun rateMovie(@Path("movie_id") movieId: Long, @Body request: RateMovieRequest): Response<PostResponse>

}