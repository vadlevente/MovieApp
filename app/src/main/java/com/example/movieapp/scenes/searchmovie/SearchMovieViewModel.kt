package com.example.movieapp.scenes.searchmovie

import com.example.movieapp.datasource.SearchDataSource
import com.example.movieapp.models.state.Content
import com.example.movieapp.models.state.Error
import com.example.movieapp.models.state.ViewState
import com.example.movieapp.scenes.common.viewmodel.MultiPageMovieListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchMovieViewModel : MultiPageMovieListViewModel(), KoinComponent {

    private val searchDataSource: SearchDataSource by inject()

    init {
        setEmptyState()
    }

    fun onSearchTextChanged(text: String, page: Long = 1) = launch {
        try {
            if (text.isEmpty()) {
                setEmptyState()
                return@launch
            }
            if(page == 1L) {
                setLoadingState()
            }
            getData(text, page)
        } catch (t: Throwable) {
            setErrorState(SearchMovieError(text))
        }
    }

    override fun onRetryTapped(errorState: ViewState) {
        (errorState as? SearchMovieError)?.let {
            onSearchTextChanged(it.query)
        }
    }

    override fun loadNextPageData(pageNumber: Long) {
        (state.value as? SearchMovieContent)?.let {
            onSearchTextChanged(it.query, pageNumber)
        }
    }

    private suspend fun getData(query: String, page: Long) {
        getConfiguration()
        getMovies(query, page)
        setListDependentState(SearchMovieContent(query))
    }

    private suspend fun getMovies(query: String, page: Long) {
        val movies = searchDataSource.searchMovie(query, page)
        submitList(movies)
    }

}

data class SearchMovieContent(val query: String) : Content()
data class SearchMovieError(val query: String) : Error()