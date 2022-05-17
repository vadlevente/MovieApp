package com.example.movieapp.scenes.common.viewmodel

import com.example.movieapp.models.movieoverview.MoreIndicatorItem
import com.example.movieapp.models.movieoverview.MovieQueryResult
import com.example.movieapp.models.movieoverview.MoviesListItem
import com.example.movieapp.models.state.Error

abstract class MultiPageMovieListViewModel(private val pagingEnabled: Boolean = true): MovieListViewModel() {

    private var currentPage: Long = 1
    private var totalPages: Long = 1
    private var isLoading = true

    abstract fun loadNextPageData(pageNumber: Long)

    fun onPageEndReached(){
        if(!pagingEnabled || _listItems.isEmpty() || isLoading) return
        if(currentPage < totalPages){
            loadNextPage(currentPage + 1, _listItems)
        }
    }

    override fun submitList(movies: MovieQueryResult) {
        finishLoadingMore()
        super.submitList(movies)
        currentPage = movies.page
        totalPages = movies.totalPages
    }

    override fun setErrorState(error: Error?) {
        if(_listItems.isEmpty()) {
            super.setErrorState(error)
        } else {
            finishLoadingMore()
            setContentState()
        }
    }

    private fun finishLoadingMore(){
        isLoading = false
        removeMoreIndicator()
    }

    private fun removeMoreIndicator() {
        if (_listItems.lastOrNull() is MoreIndicatorItem) {
            _listItems = _listItems.dropLast(1)
        }
    }

    private fun loadNextPage(pageNumber: Long, currentList: List<MoviesListItem>) {
        addLoadingItemToEndOfList(currentList)
        isLoading = true
        loadNextPageData(pageNumber)
    }

    private fun addLoadingItemToEndOfList(currentList: List<MoviesListItem>) {
        val currentListWithMoreIndicator = currentList.toMutableList()
        currentListWithMoreIndicator.add(MoreIndicatorItem())
        _listItems = currentListWithMoreIndicator
    }


}