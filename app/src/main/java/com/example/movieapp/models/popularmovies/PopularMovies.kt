package com.example.movieapp.models.popularmovies

data class PopularMovies(
    val page: Long,
    val results: List<MovieOverview>,
    val totalPages: Long
)