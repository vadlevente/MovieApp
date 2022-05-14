package com.example.movieapp.scenes.searchmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentPopularMoviesBinding
import com.example.movieapp.databinding.FragmentSearchMovieBinding
import com.example.movieapp.extensions.navController
import com.example.movieapp.scenes.common.ToolbarFragment
import com.example.movieapp.scenes.popularmovies.PopularMoviesFragmentDirections
import com.example.movieapp.scenes.popularmovies.PopularMoviesViewModel
import com.example.movieapp.scenes.popularmovies.adapter.MoviesAdapter
import com.example.movieapp.scenes.popularmovies.adapter.PopularMoviesAdaperListener

class SearchMovieFragment: ToolbarFragment(), PopularMoviesAdaperListener {

    private lateinit var binding: FragmentSearchMovieBinding

    private val viewModel by viewModels<SearchMovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_movie, container, false)
        binding.viewModel = viewModel
        binding.adapter = MoviesAdapter(this)
        binding.lifecycleOwner = viewLifecycleOwner
        setSearchViewListener()
        return binding.root
    }

    private fun setSearchViewListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.onSearchTextSubmitted(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onSearchTextChanged(newText ?: "")
                return true
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.onActionViewExpanded()
    }

    override fun onMovieClicked(movieId: Long) {
        navController?.navigate(SearchMovieFragmentDirections.navigateSearchMovieToMovieDetails(movieId))
    }

}