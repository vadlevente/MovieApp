package com.example.movieapp.databinding.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.models.ImageConfiguration
import com.example.movieapp.models.movieoverview.MovieOverview
import com.example.movieapp.models.movieoverview.MoviesListItem
import com.example.movieapp.scenes.common.adapter.MoviesAdapter
import com.example.movieapp.scenes.common.viewmodel.MultiPageMovieListViewModel

@BindingAdapter(value = ["submitList", "setConfiguration"])
fun submitList(
    recyclerView: RecyclerView,
    list: List<MoviesListItem>?,
    imageConfiguration: ImageConfiguration?
) {
    val adapter = recyclerView.adapter as? MoviesAdapter
    adapter?.apply {
        if (!list.isNullOrEmpty() && imageConfiguration != null) {
            val shouldScrollToTopAfterUpdate = hasListChanged(currentList, list)
            this.imageConfiguration = imageConfiguration
            submitList(list){
                if (shouldScrollToTopAfterUpdate) {
                    recyclerView.scrollToPosition(0)
                }
            }
        }
    }
}

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: MoviesAdapter) {
    recyclerView.adapter = adapter
}

@BindingAdapter("setScrollListener")
fun setRecyclerViewScrollListener(
    recyclerView: RecyclerView,
    viewModel: MultiPageMovieListViewModel
) {
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
            val list = viewModel.listItems.value
            if (isLastItemFullyVisible(layoutManager, list)) {
                viewModel.onPageEndReached()
            }
        }
    })
}


private fun hasListChanged(currentList: List<MoviesListItem>, newList: List<MoviesListItem>): Boolean {
    val currentFirstItem = currentList.firstOrNull() as? MovieOverview
    val newFirstItem = newList.firstOrNull() as? MovieOverview
    return currentFirstItem?.id != newFirstItem?.id
}

private fun isLastItemFullyVisible(
    layoutManager: LinearLayoutManager?,
    list: List<MoviesListItem>?
): Boolean {
    return layoutManager != null
            && list != null
            && layoutManager.findLastCompletelyVisibleItemPosition() == list.size - 1
}