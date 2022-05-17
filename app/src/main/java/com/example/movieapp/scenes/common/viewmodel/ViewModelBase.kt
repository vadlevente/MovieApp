package com.example.movieapp.scenes.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.datasource.ConfigurationDataSource
import com.example.movieapp.models.ImageConfiguration
import com.example.movieapp.models.state.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ErrorHandlingViewModel{
    fun onRetryTapped(errorState: ViewState)
}

abstract class ViewModelBase:
    ViewModel(),
    ErrorHandlingViewModel,
    KoinComponent {

    private val configurationDataSource: ConfigurationDataSource by inject()

    private val _imageConfiguration = MutableLiveData<ImageConfiguration>()
    val imageConfiguration: LiveData<ImageConfiguration> = _imageConfiguration

    val state = MutableLiveData<ViewState>()

    init {
        state.value = Loading
    }

    protected suspend fun getConfiguration(){
        val configuration = configurationDataSource.getConfiguration()
        _imageConfiguration.postValue(configuration)
    }

    protected fun launch(body: suspend () -> Unit): Job {
        return viewModelScope.launch(Dispatchers.IO){body()}
    }

    protected fun setLoadingState(){
        state.postValue(Loading)
    }

    protected fun setContentState(content: Content? = null){
        state.postValue(content ?: Content())
    }

    protected open fun setErrorState(error: Error? = null){
        state.postValue(error ?: Error())
    }

    protected fun setEmptyState(){
        state.postValue(Empty)
    }

    protected fun isInErrorState(): Boolean{
        return state.value is Error
    }

}