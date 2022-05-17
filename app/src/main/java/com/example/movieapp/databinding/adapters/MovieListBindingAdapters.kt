package com.example.movieapp.databinding.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.models.ImageConfiguration
import com.example.movieapp.models.movieoverview.MoreIndicatorItem
import com.example.movieapp.models.movieoverview.MoviesListItem
import com.example.movieapp.scenes.common.viewmodel.MultiPageMovieListViewModel
import com.example.movieapp.scenes.popularmovies.adapter.MoviesAdapter

@BindingAdapter(value = ["submitList", "setConfiguration"])
fun submitList(recyclerView: RecyclerView, list: List<MoviesListItem>?, imageConfiguration: ImageConfiguration?) {
    val adapter = recyclerView.adapter as? MoviesAdapter
    adapter?.apply {
        if(list != null && imageConfiguration != null) {
            items = list
            this.imageConfiguration = imageConfiguration
            notifyDataSetChanged()
            if(list.lastOrNull() is MoreIndicatorItem){
                recyclerView.scrollToPosition(list.size - 1)
            }
        }
    }
}

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: MoviesAdapter){
    recyclerView.adapter = adapter
}

@BindingAdapter("setScrollListener")
fun setRecyclerViewScrollListener(recyclerView: RecyclerView, viewModel: MultiPageMovieListViewModel){
    recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
            val list = viewModel.listItems.value
            if(layoutManager != null
                && list != null
                && layoutManager.findLastCompletelyVisibleItemPosition() == list.size - 1) {
                viewModel.onPageEndReached()
            }
        }
    })
}