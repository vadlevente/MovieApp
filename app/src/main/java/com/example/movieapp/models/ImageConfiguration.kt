package com.example.movieapp.models

import com.google.gson.annotations.SerializedName

data class MovieConfiguration(
    @SerializedName("images")
    val imageConfiguration: ImageConfiguration
)

data class ImageConfiguration(
    @SerializedName("secure_base_url")
    val baseUrl: String,
    @SerializedName("backdrop_sizes")
    val imageSizes: List<String>
)
