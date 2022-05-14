package com.example.movieapp.models

import com.example.movieapp.models.MovieOverview

data class MovieQueryResult(
    val page: Long,
    val results: List<MovieOverview>,
    val totalPages: Long
)