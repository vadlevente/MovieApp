package com.example.movieapp.network.api

import com.example.movieapp.models.ImageConfiguration
import com.example.movieapp.models.MovieDetails
import com.example.movieapp.models.popularmovies.PopularMovies
import com.example.movieapp.network.model.requests.RateMovieRequest
import com.example.movieapp.network.model.response.PostResponse
import retrofit2.Response
import retrofit2.http.*

interface MovieApi {

    @GET("${prefix}/popular")
    suspend fun getPopularMovies(@Query("page") page: Long): Response<PopularMovies>

    @GET("${prefix}/movie/{movieId}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Long): Response<MovieDetails>

    @POST("${prefix}/movie/{movieId}/rating")
    suspend fun rateMovie(@Path("movie_id") movieId: Long, @Body request: RateMovieRequest): Response<PostResponse>

    companion object{
        const val prefix = "movie"
    }

}