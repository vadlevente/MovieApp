package com.example.movieapp.mock

import com.example.movieapp.models.movieoverview.MovieQueryResult
import com.example.movieapp.network.api.SearchApi
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import kotlin.math.ceil

class MockSearchApi: MockApi(), SearchApi {
    override suspend fun searchMovie(query: String, page: Long): Response<MovieQueryResult> {
        getErrorIfNoInternet<MovieQueryResult>()?.let { return it }
        val results = MockMovies.movies.filter { it.title.startsWith(query) }
        if (results.isEmpty()) {
            return if(page == 1L) {
                Response.success(
                    MovieQueryResult(
                        1,
                        emptyList(),
                        1
                    )
                )
            } else {
                getNotFoundError()
            }
        }
        if(page > ceil(results.size / 3.0)){
            return getNotFoundError()
        }
        val startIndex = (page.toInt() - 1) * 3
        return Response.success(
            MovieQueryResult(
                page,
                results.subList(startIndex, startIndex + 3),
                3
            )
        )
    }

    private fun getNotFoundError(): Response<MovieQueryResult>{
        return Response.error(
            404,
            ResponseBody.create(MediaType.get("application/json"), "error")
        )
    }

}