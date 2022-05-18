package com.example.movieapp.scenes.searchmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSearchMovieBinding
import com.example.movieapp.extensions.navController
import com.example.movieapp.extensions.showKeyboard
import com.example.movieapp.scenes.common.ToolbarFragment
import com.example.movieapp.scenes.common.adapter.MoviesAdapter
import com.example.movieapp.scenes.common.adapter.PopularMoviesAdapterListener

class SearchMovieFragment: ToolbarFragment(), PopularMoviesAdapterListener {

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.apply {
            onActionViewExpanded()
            showKeyboard()
        }
    }

    override fun onMovieClicked(movieId: Long) {
        navController?.navigate(SearchMovieFragmentDirections.navigateSearchMovieToMovieDetails(movieId))
    }

}