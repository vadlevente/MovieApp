package com.example.movieapp.scenes.popularmovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.BR
import com.example.movieapp.R
import com.example.movieapp.models.popularmovies.MovieOverview
import com.example.movieapp.models.popularmovies.PopularMoviesListItem

class PopularMoviesAdapter: RecyclerView.Adapter<PopularMoviesAdapter.MovieViewHolder>() {

    var items = listOf<MovieOverview>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_popular_movies, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MovieViewHolder(private val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: MovieOverview){
            binding.setVariable(BR.movie, item)
            binding.executePendingBindings()
        }
    }

}