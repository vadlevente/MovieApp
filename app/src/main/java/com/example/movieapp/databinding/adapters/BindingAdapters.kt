package com.example.movieapp.databinding.adapters

import android.net.Uri
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.models.Genre
import com.example.movieapp.models.ImageConfiguration
import com.example.movieapp.models.MovieOverview
import com.example.movieapp.models.state.Empty
import com.example.movieapp.models.state.Error
import com.example.movieapp.models.state.Loading
import com.example.movieapp.models.state.ViewState
import com.example.movieapp.scenes.popularmovies.adapter.MoviesAdapter
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter(value = ["submitList", "setConfiguration"])
fun submitList(recyclerView: RecyclerView, list: List<MovieOverview>?, imageConfiguration: ImageConfiguration?) {
    val adapter = recyclerView.adapter as? MoviesAdapter
    adapter?.apply {
        if(list != null && imageConfiguration != null) {
            items = list
            this.imageConfiguration = imageConfiguration
            notifyDataSetChanged()
        }
    }
}

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: MoviesAdapter){
    recyclerView.adapter = adapter
}

@BindingAdapter(value = ["setImage", "setImageConfiguration"])
fun setImage(imageView: ImageView, path: String?, imageConfiguration: ImageConfiguration?){
    var uri: Uri? = null
    if(path != null && imageConfiguration != null) {
        val urlPath =
            "${imageConfiguration.baseUrl}${imageConfiguration.imageSizes.lastOrNull()}$path"
        uri = Uri.parse(urlPath)
    }
    Glide.with(imageView.context)
        .load(uri)
        .placeholder(R.drawable.ic_film_placeholder)
        .error(R.drawable.ic_film_placeholder)
        .into(imageView)
}

@BindingAdapter("setSimpleDateText")
fun setSimpleDateText(textView: TextView, date: Date?){
    if(date != null){
        textView.isVisible = true
        textView.text = getDateText(date, "yyyy")
    } else {
        textView.isVisible = false
    }
}

@BindingAdapter("setDetailedDateText")
fun setDetailedDateText(textView: TextView, date: Date?){
    date?.let {
        textView.text = getDateText(date, "yyyy. mm. dd.")
    }
}

@BindingAdapter("android:text")
fun setFloat(view: TextView, value: Float) {
    view.text = "$value"
}

@BindingAdapter("currencyText")
fun setCurrencyText(view: TextView, value: Long){
    val currencyValue = NumberFormat.getCurrencyInstance(Locale("en", "US")).format(value)
    view.text = currencyValue
}

@BindingAdapter("genreText")
fun setGenreText(view: TextView, genres: List<Genre>?){
    val genreString = genres?.joinToString(", ") { it.name }
    view.text = genreString
}

@BindingAdapter("setEmptyVisibility")
fun setEmptyVisibility(textView: TextView, viewState: ViewState){
    textView.isVisible = viewState is Empty
}

@BindingAdapter("setErrorVisibility")
fun setErrorVisibility(textView: TextView, viewState: ViewState){
    textView.isVisible = viewState is Error
}

@BindingAdapter("cover:setProgressVisibility")
fun setProgressVisibility(progressBar: ProgressBar, viewState: ViewState){
    progressBar.isVisible = viewState is Loading
}

private fun getDateText(date: Date, format: String): String{
    val dateFormat = SimpleDateFormat(format)
    return dateFormat.format(date)
}