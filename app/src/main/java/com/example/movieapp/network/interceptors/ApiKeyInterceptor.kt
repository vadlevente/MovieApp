package com.example.movieapp.network.interceptors

import com.example.movieapp.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.core.component.KoinComponent

class ApiKeyInterceptor: Interceptor, KoinComponent {

    private val apiKey = BuildConfig.API_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = getNewRequest(originalRequest)
        return chain.proceed(newRequest)
    }

    private fun getNewRequest(originalRequest: Request): Request {
        val url = getNewUrl(originalRequest.url())
        return originalRequest
            .newBuilder()
            .url(url)
            .build()
    }

    private fun getNewUrl(originalUrl: HttpUrl): HttpUrl{
        return originalUrl
            .newBuilder()
            .addQueryParameter(parameterApiKey, apiKey)
            .build()
    }

    companion object {
        const val parameterApiKey = "api_key"
    }


}