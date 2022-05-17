package com.example.movieapp.models.state

sealed class ViewState

object Loading : ViewState()

open class Content: ViewState()

open class Error: ViewState()

object Empty : ViewState()