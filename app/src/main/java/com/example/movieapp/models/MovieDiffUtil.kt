package com.example.movieapp.models

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.models.movieoverview.MoreIndicatorItem
import com.example.movieapp.models.movieoverview.MovieOverview
import com.example.movieapp.models.movieoverview.MoviesListItem

class MovieDiffUtil: DiffUtil.ItemCallback<MoviesListItem>() {

    override fun areItemsTheSame(oldItem: MoviesListItem, newItem: MoviesListItem): Boolean {
        return if(oldItem is MovieOverview && newItem is MovieOverview){
            oldItem.id == newItem.id
        } else oldItem is MoreIndicatorItem && newItem is MoreIndicatorItem
    }

    override fun areContentsTheSame(oldItem: MoviesListItem, newItem: MoviesListItem): Boolean {
        return if(oldItem is MovieOverview && newItem is MovieOverview){
            oldItem.id == newItem.id
                    && oldItem.title == newItem.title
                    && oldItem.rating == newItem.rating
                    && oldItem.imagePath == newItem.imagePath
        } else oldItem is MoreIndicatorItem && newItem is MoreIndicatorItem
    }
}