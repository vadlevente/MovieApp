package com.example.movieapp.datasource

import com.example.movieapp.models.MovieDetails
import com.example.movieapp.models.popularmovies.PopularMovies
import com.example.movieapp.network.api.MovieApi
import com.example.movieapp.network.model.requests.RateMovieRequest

interface MovieDataSource{
    suspend fun getPopularMovies(page: Long): PopularMovies
    suspend fun getMovieDetails(movieId: Long): MovieDetails
    suspend fun rateMovie(movieId: Long, rating: Float)
}

class NetworkMovieDataSource(private val movieApi: MovieApi): DataSourceBase(), MovieDataSource {

    override suspend fun getPopularMovies(page: Long): PopularMovies {
        val response = movieApi.getPopularMovies(page)
        return handleGetResponse(response)
    }

    override suspend fun getMovieDetails(movieId: Long): MovieDetails {
        val response = movieApi.getMovieDetails(movieId)
        return handleGetResponse(response)
    }

    override suspend fun rateMovie(movieId: Long, rating: Float) {
        val response = movieApi.rateMovie(movieId, RateMovieRequest(rating))
        handlePostResponse(response)
    }
}