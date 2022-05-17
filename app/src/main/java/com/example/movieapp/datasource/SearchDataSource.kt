package com.example.movieapp.datasource

import com.example.movieapp.models.MovieOverview
import com.example.movieapp.network.api.SearchApi

interface SearchDataSource{
    suspend fun searchMovie(query: String): List<MovieOverview>
}

class NetworkSearchDataSource(private val searchApi: SearchApi): DataSourceBase(), SearchDataSource {

    override suspend fun searchMovie(query: String): List<MovieOverview> {
        if(!movieSearchCache.containsKey(query)) {
            val response = searchApi.searchMovie(query)
            movieSearchCache[query] = handleGetResponse(response).results
        }
        return getMovieFromCache(query)
    }

    private fun getMovieFromCache(query: String): List<MovieOverview>{
        return movieSearchCache[query] ?: emptyList()
    }

    companion object{
        var movieSearchCache = mutableMapOf<String, List<MovieOverview>>()
    }
}