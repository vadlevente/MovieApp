package com.example.movieapp.scenes.searchmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.datasource.SearchDataSource
import com.example.movieapp.models.MovieOverview
import com.example.movieapp.scenes.common.viewmodel.ViewModelBase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchMovieViewModel: ViewModelBase(), KoinComponent {

    private val searchDataSource: SearchDataSource by inject()

    private val _listItems = MutableLiveData<List<MovieOverview>>()
    val listItems: LiveData<List<MovieOverview>> = _listItems

    init {
        setEmptyState()
    }

    fun onSearchTextChanged(text: String){
        viewModelScope.launch{
            try {
                setLoadingState()
                getData(text, true)
            } catch (t: Throwable){
                setErrorState()
            }
        }
    }

    fun onSearchTextSubmitted(text: String){
        viewModelScope.launch{
            try {
                setLoadingState()
                getData(text, false)
            } catch (t: Throwable){
                setErrorState()
            }
        }
    }

    private suspend fun getData(query: String, fromCache: Boolean = true) {
        getConfiguration()
        getMovies(query, fromCache)
        if(_listItems.value.isNullOrEmpty()){
            setEmptyState()
        } else {
            setContentState()
        }
    }

    private suspend fun getMovies(query: String, fromCache: Boolean = true) {
        val movies = if(fromCache) {
            searchDataSource.searchMovieFromCache(query)
        } else {
            searchDataSource.searchMovie(query)
        }
        submitList(movies)
    }

    private fun submitList(movies: List<MovieOverview>){
       _listItems.value = movies
    }

}