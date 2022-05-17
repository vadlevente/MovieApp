package com.example.movieapp.models.state

sealed class ViewState

class Loading: ViewState()

class Content: ViewState()

open class Error: ViewState()

class Empty: ViewState()