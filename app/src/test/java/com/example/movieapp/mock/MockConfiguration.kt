package com.example.movieapp.mock

import com.example.movieapp.models.ImageConfiguration
import com.example.movieapp.models.MovieConfiguration

object MockConfiguration {
    lateinit var configuration: MovieConfiguration

    var internetAvailable = true

    fun setup(){
        configuration = MovieConfiguration(
            ImageConfiguration(
                "baseUrl",
                listOf("small", "big")
            )
        )
        internetAvailable = true
    }
}