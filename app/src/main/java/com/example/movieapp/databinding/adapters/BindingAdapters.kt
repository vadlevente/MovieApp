package com.example.movieapp.databinding.adapters

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.models.popularmovies.MovieOverview
import com.example.movieapp.models.popularmovies.PopularMoviesListItem
import com.example.movieapp.scenes.popularmovies.adapter.PopularMoviesAdapter

@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: List<MovieOverview>?) {
    val adapter = recyclerView.adapter as? PopularMoviesAdapter
    adapter?.apply {
        list?.let {
            items = list
            notifyDataSetChanged()
        }
    }
}

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: PopularMoviesAdapter){
    recyclerView.adapter = adapter
}