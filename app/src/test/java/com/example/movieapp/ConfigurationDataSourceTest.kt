package com.example.movieapp

import com.example.movieapp.datasource.ConfigurationDataSource
import com.example.movieapp.datasource.NetworkConfigurationDataSource
import com.example.movieapp.mock.MockConfiguration
import com.example.movieapp.mock.MockConfigurationApi
import com.example.movieapp.models.ImageConfiguration
import com.example.movieapp.models.MovieConfiguration
import com.example.movieapp.network.model.NetworkError
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class ConfigurationDataSourceTest: DataSourceTestBase() {

    private lateinit var dataSource: ConfigurationDataSource

    override fun setupDependencies() {
        dataSource = NetworkConfigurationDataSource(MockConfigurationApi())
    }

    @Test
    fun test_configuration_query() = runBlocking{
        val configuration = dataSource.getConfiguration()
        Assert.assertEquals("baseUrl", configuration.baseUrl)
        Assert.assertEquals("small", configuration.imageSizes[0])
        Assert.assertEquals("big", configuration.imageSizes[1])
    }

    @Test
    fun test_configuration_query_cache() = runBlocking{
        val configuration = dataSource.getConfiguration()
        Assert.assertEquals("baseUrl", configuration.baseUrl)
        Assert.assertEquals("small", configuration.imageSizes[0])
        Assert.assertEquals("big", configuration.imageSizes[1])
        MockConfiguration.configuration = MovieConfiguration(
            ImageConfiguration(
                "baseUrlModified",
                listOf("small")
            )
        )
        val configurationAfterModification = dataSource.getConfiguration()
        Assert.assertEquals("baseUrl", configurationAfterModification.baseUrl)
        Assert.assertEquals("small", configurationAfterModification.imageSizes[0])
        Assert.assertEquals("big", configurationAfterModification.imageSizes[1])
    }

    @Test(expected = NetworkError::class)
    fun test_no_internet_error() = runBlocking {
        MockConfiguration.internetAvailable = false
        dataSource.getConfiguration()
        return@runBlocking
    }

}