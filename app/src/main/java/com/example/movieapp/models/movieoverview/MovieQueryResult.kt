package com.example.movieapp.models.movieoverview

import com.google.gson.annotations.SerializedName

data class MovieQueryResult(
    val page: Long,
    val results: List<MovieOverview>,
    @SerializedName("total_pages")
    val totalPages: Long
)