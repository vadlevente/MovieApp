package com.example.movieapp.scenes.common.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.movieapp.models.movieoverview.MovieQueryResult
import com.example.movieapp.models.movieoverview.MoviesListItem
import com.example.movieapp.models.state.Content

abstract class MovieListViewModel: ViewModelBase() {

    protected var _listItems = listOf<MoviesListItem>()
        set(value) {
            field = value
            listItems.postValue(value)
        }
    val listItems = MutableLiveData<List<MoviesListItem>>(emptyList())



    protected open fun submitList(movies: MovieQueryResult){
        addMoviesToList(movies)
    }

    protected fun setListDependentState(content: Content? = null){
        if(_listItems.isEmpty()){
            setEmptyState()
        } else {
            setContentState(content)
        }
    }

    private fun addMoviesToList(movies: MovieQueryResult) {
        _listItems = if (_listItems.isEmpty() || movies.page == 1L) {
            movies.results
        } else {
            val newList = _listItems.toMutableList()
            newList.addAll(movies.results)
            newList
        }
    }

}