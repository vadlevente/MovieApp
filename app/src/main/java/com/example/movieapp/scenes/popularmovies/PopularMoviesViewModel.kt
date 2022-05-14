package com.example.movieapp.scenes.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.datasource.ConfigurationDataSource
import com.example.movieapp.datasource.MovieDataSource
import com.example.movieapp.models.ImageConfiguration
import com.example.movieapp.models.popularmovies.MovieOverview
import com.example.movieapp.models.popularmovies.PopularMovies
import com.example.movieapp.scenes.viewmodel.ViewModelBase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PopularMoviesViewModel: ViewModelBase(), KoinComponent {

    private val movieDataSource: MovieDataSource by inject()
    private val configurationDataSource: ConfigurationDataSource by inject()

    private val _listItems = MutableLiveData<List<MovieOverview>>()
    val listItems: LiveData<List<MovieOverview>> = _listItems

    private val _imageConfiguration = MutableLiveData<ImageConfiguration>()
    val imageConfiguration: LiveData<ImageConfiguration> = _imageConfiguration

    init {
        loadPopularMovies()
    }

    fun loadPopularMovies(page: Long = 1) {
        viewModelScope.launch{
            try {
                if(isInErrorState()) {
                    setLoadingState()
                }
                getData(page)
                setContentState()
            } catch (t: Throwable){
                setErrorState()
            }
        }
    }

    private suspend fun getData(page: Long) {
        getConfiguration()
        getMovies(page)
    }

    private suspend fun getMovies(page: Long) {
        val movies = movieDataSource.getPopularMovies(page)
        submitList(movies)
    }

    private suspend fun getConfiguration() {
        _imageConfiguration.value = configurationDataSource.getConfiguration()
    }

    private fun submitList(movies: PopularMovies){
       _listItems.value = movies.results
    }

}