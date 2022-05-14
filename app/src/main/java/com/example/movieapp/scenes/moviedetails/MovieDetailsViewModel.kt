package com.example.movieapp.scenes.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.datasource.MovieDataSource
import com.example.movieapp.models.MovieDetails
import com.example.movieapp.scenes.common.viewmodel.ViewModelBase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MovieDetailsViewModel: ViewModelBase(), KoinComponent {

    private val movieDataSource: MovieDataSource by inject()

    private val _details = MutableLiveData<MovieDetails>()
    val details: LiveData<MovieDetails> = _details

    fun loadMovieDetails(movieId: Long){
        viewModelScope.launch{
            try {
                if(isInErrorState()) {
                    setLoadingState()
                }
                getData(movieId)
                setContentState()
            } catch (t: Throwable){
                setErrorState()
            }
        }
    }

    private suspend fun getData(movieId: Long){
        getConfiguration()
        getMovieDetails(movieId)
    }

    private suspend fun getMovieDetails(movieId: Long){
        _details.value = movieDataSource.getMovieDetails(movieId)
    }

}