package com.example.movieapp.datasource

import com.example.movieapp.network.model.NetworkError
import com.example.movieapp.network.model.response.PostResponse
import retrofit2.Response

open class DataSourceBase {

    protected fun <T> handleGetResponse(response: Response<T>): T {
        val body = response.body()
        if(response.isSuccessful && body != null){
            return body
        } else {
            throw NetworkError()
        }
    }

}