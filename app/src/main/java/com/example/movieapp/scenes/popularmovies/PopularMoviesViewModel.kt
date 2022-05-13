package com.example.movieapp.scenes.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.datasource.MovieDataSource
import com.example.movieapp.models.popularmovies.MovieOverview
import com.example.movieapp.models.popularmovies.PopularMovies
import com.example.movieapp.models.popularmovies.PopularMoviesListItem
import com.example.movieapp.models.state.Error
import com.example.movieapp.models.state.Loading
import com.example.movieapp.scenes.viewmodel.ViewModelBase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PopularMoviesViewModel: ViewModelBase(), KoinComponent {

    private val dataSource: MovieDataSource by inject()

    private val _listItems = MutableLiveData<List<MovieOverview>>()
    val listItems: LiveData<List<MovieOverview>> = _listItems


    init {
        loadPopularMovies()
    }

    fun loadPopularMovies(page: Long = 1) {
        viewModelScope.launch{
//            try {
                if(isInErrorState()) {
                    setLoadingState()
                }
                val movies = dataSource.getPopularMovies(page)
                submitList(movies)
                setContentState()
//            } catch (t: Throwable){
//                setErrorState()
//            }
        }
    }

    private fun submitList(movies: PopularMovies){
       _listItems.value = movies.results
    }

}