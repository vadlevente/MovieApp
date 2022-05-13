package com.example.movieapp.scenes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.models.state.Content
import com.example.movieapp.models.state.Error
import com.example.movieapp.models.state.Loading
import com.example.movieapp.models.state.ViewState

open class ViewModelBase: ViewModel() {

    val state = MutableLiveData<ViewState>()

    init {
        state.value = Loading()
    }

    protected fun setLoadingState(){

    }

    protected fun setContentState(){
        state.value = Content()
    }

    protected fun setErrorState(){
        state.value = Error()
    }

    protected fun isInErrorState(): Boolean{
        return state.value is Error
    }

}