package com.example.movieapp

import com.example.movieapp.datasource.MovieDataSource
import com.example.movieapp.datasource.NetworkMovieDataSource
import com.example.movieapp.mock.MockConfiguration
import com.example.movieapp.mock.MockMovieApi
import com.example.movieapp.network.api.ConfigurationApi
import com.example.movieapp.network.model.NetworkError
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MovieDataSourceTest: DataSourceTestBase() {

    lateinit var movieDataSource: MovieDataSource

    override fun setupDependencies() {
        movieDataSource = NetworkMovieDataSource(MockMovieApi())
    }

    @Test
    fun test_get_popular_movies_first_page() = runBlocking{
        val movies = movieDataSource.getPopularMovies(1)
        Assert.assertEquals(1, movies.page)
        Assert.assertEquals(3, movies.totalPages)
        Assert.assertEquals(1, movies.results[0].id)
        Assert.assertEquals(2, movies.results[1].id)
        Assert.assertEquals(3, movies.results[2].id)
    }

    @Test
    fun test_get_popular_movies_second_page() = runBlocking{
        val movies = movieDataSource.getPopularMovies(2)
        Assert.assertEquals(2, movies.page)
        Assert.assertEquals(3, movies.totalPages)
        Assert.assertEquals(4, movies.results[0].id)
        Assert.assertEquals(5, movies.results[1].id)
        Assert.assertEquals(6, movies.results[2].id)
    }

    @Test(expected = NetworkError::class)
    fun test_get_popular_movies_non_existent_page() = runBlocking{
        movieDataSource.getPopularMovies(5)
        return@runBlocking
    }

    @Test(expected = NetworkError::class)
    fun test_get_popular_movies_no_internet() = runBlocking{
        MockConfiguration.internetAvailable = false
        movieDataSource.getPopularMovies(1)
        return@runBlocking
    }

    @Test
    fun test_get_legitimate_movie_details() = runBlocking {
        val details = movieDataSource.getMovieDetails(1)
        Assert.assertEquals(1, details.id)
        Assert.assertEquals("cím 1", details.title)
        Assert.assertEquals(1.0f, details.rating)
        Assert.assertEquals(0, details.budget)
        Assert.assertEquals(0, details.revenue)
    }

    @Test(expected = NetworkError::class)
    fun test_non_existent_movie_details() = runBlocking {
        movieDataSource.getMovieDetails(100)
        return@runBlocking
    }

    @Test(expected = NetworkError::class)
    fun test_movie_details_no_internet() = runBlocking {
        MockConfiguration.internetAvailable = false
        movieDataSource.getMovieDetails(1)
        return@runBlocking
    }

}