package com.example.movieapp.scenes.moviedetails

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.datasource.MovieDataSource
import com.example.movieapp.models.MovieDetails
import com.example.movieapp.models.state.Content
import com.example.movieapp.models.state.Error
import com.example.movieapp.models.state.ViewState
import com.example.movieapp.scenes.common.viewmodel.ViewModelBase
import com.example.movieapp.scenes.searchmovie.SearchMovieError
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
                setErrorState(MovieDetailsError(movieId))
            }
        }
    }

    override fun onRetryTapped(errorState: ViewState) {
        (errorState as? MovieDetailsError)?.let {
            loadMovieDetails(it.movieId)
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

data class MovieDetailsError(val movieId: Long): Error()