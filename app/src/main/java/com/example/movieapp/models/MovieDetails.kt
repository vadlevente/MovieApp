package com.example.movieapp.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.*

data class MovieDetails(
    @SerializedName("id")
    val id: Long,
    @SerializedName("backdrop_path")
    val imagePath: String,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: Date,
    @SerializedName("vote_average")
    val rating: Float,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("budget")
    val budget: Long,
    @SerializedName("revenue")
    val revenue: String?
)

data class Genre(
    @SerializedName("name")
    val name: String,
)
