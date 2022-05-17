package com.example.movieapp.models.movieoverview

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieOverview(
    @SerializedName("id")
    val id: Long,
    @SerializedName("backdrop_path")
    val imagePath: String?,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: Date?,
    @SerializedName("vote_average")
    val rating: Float
): MoviesListItem