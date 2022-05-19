package com.example.movieapp.mock

import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

abstract class MockApi {
    fun <T> getErrorIfNoInternet(): Response<T>?{
        if(!MockConfiguration.internetAvailable){
            return Response.error(500, ResponseBody.create(MediaType.get("application/json"), "error"))
        }
        return null
    }
}