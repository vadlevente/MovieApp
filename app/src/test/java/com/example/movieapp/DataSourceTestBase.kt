package com.example.movieapp

import com.example.movieapp.mock.MockConfiguration
import com.example.movieapp.mock.MockMovies
import com.example.movieapp.models.ImageConfiguration
import com.example.movieapp.models.MovieConfiguration
import org.junit.Before

abstract class DataSourceTestBase {

    abstract fun setupDependencies()

    @Before
    fun setup(){
        MockConfiguration.setup()
        MockMovies.setup()
        setupDependencies()
    }

}