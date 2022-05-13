package com.example.movieapp.scenes.popularmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentPopularMoviesBinding
import com.example.movieapp.scenes.popularmovies.adapter.PopularMoviesAdapter

class PopularMoviesFragment: Fragment() {

    private lateinit var binding: FragmentPopularMoviesBinding

    private val viewModel by viewModels<PopularMoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular_movies, container, false)
        binding.viewModel = viewModel
        binding.adapter = PopularMoviesAdapter()
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

}