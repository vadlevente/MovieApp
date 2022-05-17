package com.example.movieapp.scenes.popularmovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.BR
import com.example.movieapp.R
import com.example.movieapp.models.ImageConfiguration
import com.example.movieapp.models.movieoverview.MovieOverview
import com.example.movieapp.models.movieoverview.MoviesListItem

class MoviesAdapter(private val listener: PopularMoviesAdapterListener): RecyclerView.Adapter<MoviesAdapter.MovieListViewHolder>() {

    var items = listOf<MoviesListItem>()

    var imageConfiguration: ImageConfiguration? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return when(viewType){
            viewTypeMovie -> {
                val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_movie_list, parent, false)
                MovieViewHolder(binding)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_load_more, parent, false)
                LoadMoreViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is MovieOverview -> viewTypeMovie
            else -> viewTypeLoadMore
        }
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        if(holder is MovieViewHolder) {
            val movie = items[position] as? MovieOverview
            movie?.let {
                holder.bind(movie, imageConfiguration)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    abstract inner class MovieListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    inner class MovieViewHolder(private val binding: ViewDataBinding): MovieListViewHolder(binding.root){
        fun bind(item: MovieOverview, imageConfiguration: ImageConfiguration?){
            binding.setVariable(BR.movie, item)
            binding.setVariable(BR.imageConfiguration, imageConfiguration)
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                listener.onMovieClicked(item.id)
            }
        }
    }

    inner class LoadMoreViewHolder(itemView: View): MovieListViewHolder(itemView)

    companion object{
        const val viewTypeMovie = 1
        const val viewTypeLoadMore = 2
    }

}

interface PopularMoviesAdapterListener{
    fun onMovieClicked(movieId: Long)
}