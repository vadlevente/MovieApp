package com.example.movieapp.scenes.searchmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.datasource.SearchDataSource
import com.example.movieapp.models.MovieOverview
import com.example.movieapp.models.state.Content
import com.example.movieapp.models.state.Error
import com.example.movieapp.models.state.ViewState
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
                if(text.isEmpty()){
                    setEmptyState()
                    return@launch
                }
                setLoadingState()
                getData(text)
            } catch (t: Throwable){
                setErrorState(SearchMovieError(text))
            }
        }
    }

    override fun onRetryTapped(errorState: ViewState) {
        (errorState as? SearchMovieError)?.let {
            onSearchTextChanged(it.query)
        }
    }

    private suspend fun getData(query: String) {
        getConfiguration()
        getMovies(query)
        if(_listItems.value.isNullOrEmpty()){
            setEmptyState()
        } else {
            setContentState()
        }
    }

    private suspend fun getMovies(query: String) {
        val movies = searchDataSource.searchMovie(query)
        submitList(movies)
    }

    private fun submitList(movies: List<MovieOverview>){
       _listItems.value = movies
    }

}

data class SearchMovieError(val query: String): Error()