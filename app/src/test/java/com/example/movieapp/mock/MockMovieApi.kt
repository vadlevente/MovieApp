package com.example.movieapp.mock

import com.example.movieapp.models.MovieConfiguration
import com.example.movieapp.models.MovieDetails
import com.example.movieapp.models.movieoverview.MovieOverview
import com.example.movieapp.models.movieoverview.MovieQueryResult
import com.example.movieapp.network.api.MovieApi
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.*

class MockMovieApi : MockApi(), MovieApi {
    override suspend fun getPopularMovies(page: Long): Response<MovieQueryResult> {
        getErrorIfNoInternet<MovieQueryResult>()?.let { return it }
        if (page > 3) {
            return Response.error(
                404,
                ResponseBody.create(MediaType.get("application/json"), "error")
            )
        }
        val startIndex = (page.toInt() - 1) * 3
        return Response.success(
            MovieQueryResult(
                page,
                MockMovies.movies.subList(startIndex, startIndex + 3),
                3
            )
        )
    }

    override suspend fun getMovieDetails(movieId: Long): Response<MovieDetails> {
        getErrorIfNoInternet<MovieDetails>()?.let { return it }
        val movie = MockMovies.movies.firstOrNull { it.id == movieId }
        if (movie == null) {
            return Response.error(
                404,
                ResponseBody.create(MediaType.get("application/json"), "error")
            )
        }
        return Response.success(
            MovieDetails(
                movie.id,
                movie.imagePath,
                movie.title,
                movie.releaseDate,
                movie.rating,
                "",
                emptyList(), null,
                0,
                0
            )
        )
    }

}