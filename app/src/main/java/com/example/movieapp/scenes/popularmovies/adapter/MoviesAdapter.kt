package com.example.movieapp.scenes.popularmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.BR
import com.example.movieapp.R
import com.example.movieapp.models.ImageConfiguration
import com.example.movieapp.models.MovieOverview

class MoviesAdapter(private val listener: PopularMoviesAdaperListener): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var items = listOf<MovieOverview>()

    var imageConfiguration: ImageConfiguration? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_popular_movies, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position], imageConfiguration)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MovieViewHolder(private val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: MovieOverview, imageConfiguration: ImageConfiguration?){
            binding.setVariable(BR.movie, item)
            binding.setVariable(BR.imageConfiguration, imageConfiguration)
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                listener.onMovieClicked(item.id)
            }
        }
    }

}

interface PopularMoviesAdaperListener{
    fun onMovieClicked(movieId: Long)
}