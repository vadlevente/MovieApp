package com.example.movieapp.scenes.moviedetails

import androidx.lifecycle.MutableLiveData
import com.example.movieapp.datasource.MovieDataSource
import com.example.movieapp.models.MovieDetails
import com.example.movieapp.models.state.Error
import com.example.movieapp.models.state.ViewState
import com.example.movieapp.scenes.common.viewmodel.ViewModelBase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MovieDetailsViewModel : ViewModelBase(), KoinComponent {

    private val movieDataSource: MovieDataSource by inject()

    val details = MutableLiveData<MovieDetails>()

    fun loadMovieDetails(movieId: Long) = launch {
        try {
            if (isInErrorState()) {
                setLoadingState()
            }
            getData(movieId)
            setContentState()
        } catch (t: Throwable) {
            setErrorState(MovieDetailsError(movieId))
        }
    }

    override fun onRetryTapped(errorState: ViewState) {
        (errorState as? MovieDetailsError)?.let {
            loadMovieDetails(it.movieId)
        }
    }

    private suspend fun getData(movieId: Long) {
        getConfiguration()
        getMovieDetails(movieId)
    }

    private suspend fun getMovieDetails(movieId: Long) {
        val movieDetails = movieDataSource.getMovieDetails(movieId)
        details.postValue(movieDetails)
    }

}

data class MovieDetailsError(val movieId: Long) : Error()