package com.example.movieapp.databinding.adapters

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import com.example.movieapp.scenes.searchmovie.SearchMovieViewModel

@BindingAdapter("setQueryChangeListener")
fun setQueryChangeListener(view: SearchView, viewModel: SearchMovieViewModel){
    view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            viewModel.onSearchTextChanged(newText ?: "")
            return true
        }
    })
}