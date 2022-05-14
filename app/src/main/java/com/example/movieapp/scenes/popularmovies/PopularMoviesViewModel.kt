package com.example.movieapp.scenes.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.datasource.MovieDataSource
import com.example.movieapp.models.MovieOverview
import com.example.movieapp.models.MovieQueryResult
import com.example.movieapp.scenes.common.viewmodel.ViewModelBase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PopularMoviesViewModel: ViewModelBase(), KoinComponent {

    private val movieDataSource: MovieDataSource by inject()

    private val _listItems = MutableLiveData<List<MovieOverview>>()
    val listItems: LiveData<List<MovieOverview>> = _listItems

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

    private fun submitList(movies: MovieQueryResult){
       _listItems.value = movies.results
    }

}