package com.example.movieapp.datasource

import com.example.movieapp.models.ImageConfiguration
import com.example.movieapp.network.api.ConfigurationApi

interface ConfigurationDataSource{
    suspend fun getConfiguration(): ImageConfiguration
}

class NetworkConfigurationDataSource(private val configurationApi: ConfigurationApi): DataSourceBase(), ConfigurationDataSource {

    override suspend fun getConfiguration(): ImageConfiguration {
        if(configurationCache == null){
            val response = configurationApi.getConfiguration()
            configurationCache = handleGetResponse(response).imageConfiguration
        }
        return configurationCache!!
    }

    companion object {
        private var configurationCache: ImageConfiguration? = null
    }

}