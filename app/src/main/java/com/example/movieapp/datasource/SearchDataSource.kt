package com.example.movieapp.datasource

import com.example.movieapp.models.movieoverview.MovieQueryResult
import com.example.movieapp.network.api.SearchApi

interface SearchDataSource{
    suspend fun searchMovie(query: String, page: Long): MovieQueryResult
}

class NetworkSearchDataSource(private val searchApi: SearchApi): DataSourceBase(), SearchDataSource {

    override suspend fun searchMovie(query: String, page: Long): MovieQueryResult {
        if(!movieSearchCache.containsKey(Pair(query, page))) {
            val response = searchApi.searchMovie(query, page)
            movieSearchCache[Pair(query, page)] = handleGetResponse(response)
        }
        return movieSearchCache[Pair(query, page)]!!
    }

    var movieSearchCache = mutableMapOf<Pair<String, Long>, MovieQueryResult>()

}