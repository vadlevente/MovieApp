package com.example.movieapp.mock

import com.example.movieapp.models.ImageConfiguration
import com.example.movieapp.models.MovieConfiguration
import com.example.movieapp.network.api.ConfigurationApi
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

class MockConfigurationApi: MockApi(), ConfigurationApi {
    override suspend fun getConfiguration(): Response<MovieConfiguration> {
        getErrorIfNoInternet<MovieConfiguration>()?.let { return it }
        return Response.success(MockConfiguration.configuration)
    }
}