package com.example.movieapp.network.model.requests

import com.google.gson.annotations.SerializedName

data class RateMovieRequest(
    @SerializedName("value")
    val rating: Float
)
