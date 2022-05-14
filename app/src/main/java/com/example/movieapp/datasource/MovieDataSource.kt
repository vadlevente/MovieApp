package com.example.movieapp.datasource

import com.example.movieapp.models.MovieDetails
import com.example.movieapp.models.MovieQueryResult
import com.example.movieapp.network.api.MovieApi

interface MovieDataSource{
    suspend fun getPopularMovies(page: Long): MovieQueryResult
    suspend fun getMovieDetails(movieId: Long): MovieDetails
}

class NetworkMovieDataSource(private val movieApi: MovieApi): DataSourceBase(), MovieDataSource {

    override suspend fun getPopularMovies(page: Long): MovieQueryResult {
        val response = movieApi.getPopularMovies(page)
        return handleGetResponse(response)
    }

    override suspend fun getMovieDetails(movieId: Long): MovieDetails {
        val response = movieApi.getMovieDetails(movieId)
        return handleGetResponse(response)
    }

}