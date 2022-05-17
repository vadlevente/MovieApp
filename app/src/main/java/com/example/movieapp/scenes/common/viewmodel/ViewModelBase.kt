package com.example.movieapp.scenes.common.viewmodel

import android.view.View
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.datasource.ConfigurationDataSource
import com.example.movieapp.models.ImageConfiguration
import com.example.movieapp.models.state.*
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

    protected val configurationDataSource: ConfigurationDataSource by inject()

    private val _imageConfiguration = MutableLiveData<ImageConfiguration>()
    val imageConfiguration: LiveData<ImageConfiguration> = _imageConfiguration

    val state = MutableLiveData<ViewState>()

    init {
        state.value = Loading()
    }

    protected suspend fun getConfiguration(){
        _imageConfiguration.value = configurationDataSource.getConfiguration()
    }

    protected fun setLoadingState(){
        state.value = Loading()
    }

    protected fun setContentState(){
        state.value = Content()
    }

    protected fun setErrorState(error: Error? = null){
        state.value = error ?: Error()
    }

    protected fun setEmptyState(){
        state.value = Empty()
    }

    protected fun isInErrorState(): Boolean{
        return state.value is Error
    }

}