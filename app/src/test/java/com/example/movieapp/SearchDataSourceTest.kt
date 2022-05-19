package com.example.movieapp

import com.example.movieapp.datasource.NetworkSearchDataSource
import com.example.movieapp.datasource.SearchDataSource
import com.example.movieapp.mock.MockConfiguration
import com.example.movieapp.mock.MockMovies
import com.example.movieapp.mock.MockSearchApi
import com.example.movieapp.network.model.NetworkError
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class SearchDataSourceTest: DataSourceTestBase() {

    private lateinit var searchDataSource: SearchDataSource

    override fun setupDependencies() {
        searchDataSource = NetworkSearchDataSource(MockSearchApi())
    }

    @Test
    fun test_search_first_page() = runBlocking {
        val movies = searchDataSource.searchMovie("cím", 1)
        Assert.assertEquals(1, movies.page)
        Assert.assertEquals(3, movies.totalPages)
        Assert.assertEquals(1, movies.results[0].id)
        Assert.assertEquals(2, movies.results[1].id)
        Assert.assertEquals(3, movies.results[2].id)
    }

    @Test
    fun test_search_second_page() = runBlocking {
        val movies = searchDataSource.searchMovie("cím", 2)
        Assert.assertEquals(2, movies.page)
        Assert.assertEquals(3, movies.totalPages)
        Assert.assertEquals(4, movies.results[0].id)
        Assert.assertEquals(5, movies.results[1].id)
        Assert.assertEquals(6, movies.results[2].id)
    }

    @Test(expected = NetworkError::class)
    fun test_search_non_existent_page() = runBlocking{
        searchDataSource.searchMovie("cím", 5)
        return@runBlocking
    }

    @Test
    fun test_search_no_result_first_page() = runBlocking{
        val movies = searchDataSource.searchMovie("valami", 1)
        Assert.assertEquals(1, movies.page)
        Assert.assertEquals(0, movies.results.size)
        Assert.assertEquals(1, movies.totalPages)
        return@runBlocking
    }

    @Test(expected = NetworkError::class)
    fun test_search_no_result_non_existent_page() = runBlocking{
        searchDataSource.searchMovie("valami", 5)
        return@runBlocking
    }

    @Test
    fun test_search_cache() = runBlocking {
        val movies = searchDataSource.searchMovie("cím", 1)
        Assert.assertEquals(1, movies.page)
        Assert.assertEquals(3, movies.totalPages)
        Assert.assertEquals(1, movies.results[0].id)
        Assert.assertEquals(2, movies.results[1].id)
        Assert.assertEquals(3, movies.results[2].id)
        MockMovies.movies = emptyList()
        val moviesAfterModification = searchDataSource.searchMovie("cím", 1)
        Assert.assertEquals(1, moviesAfterModification.page)
        Assert.assertEquals(3, moviesAfterModification.totalPages)
        Assert.assertEquals(1, moviesAfterModification.results[0].id)
        Assert.assertEquals(2, moviesAfterModification.results[1].id)
        Assert.assertEquals(3, moviesAfterModification.results[2].id)
    }

    @Test(expected = NetworkError::class)
    fun test_get_search_no_internet() = runBlocking{
        MockConfiguration.internetAvailable = false
        searchDataSource.searchMovie("cím", 1)
        return@runBlocking
    }

}