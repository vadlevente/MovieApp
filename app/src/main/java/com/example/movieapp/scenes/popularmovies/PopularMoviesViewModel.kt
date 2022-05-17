package com.example.movieapp.scenes.popularmovies

import com.example.movieapp.datasource.MovieDataSource
import com.example.movieapp.models.state.ViewState
import com.example.movieapp.scenes.common.viewmodel.MultiPageMovieListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PopularMoviesViewModel: MultiPageMovieListViewModel(), KoinComponent {

    private val movieDataSource: MovieDataSource by inject()

    init {
        loadPopularMovies()
    }

    override fun onRetryTapped(errorState: ViewState) {
        loadPopularMovies()
    }

    override fun loadNextPageData(pageNumber: Long) {
        loadPopularMovies(pageNumber)
    }

    private fun loadPopularMovies(page: Long = 1) = launch {
        try {
            if (isInErrorState()) {
                setLoadingState()
            }
            getData(page)
        } catch (t: Throwable) {
            setErrorState()
        }
    }

    private suspend fun getData(page: Long) {
        getConfiguration()
        getMovies(page)
        setListDependentState()
    }

    private suspend fun getMovies(page: Long) {
        val movies = movieDataSource.getPopularMovies(page)
        submitList(movies)
    }

}