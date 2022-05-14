package com.example.movieapp.databinding.adapters

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.models.ImageConfiguration
import com.example.movieapp.models.popularmovies.MovieOverview
import com.example.movieapp.scenes.popularmovies.adapter.PopularMoviesAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter(value = ["submitList", "setConfiguration"])
fun submitList(recyclerView: RecyclerView, list: List<MovieOverview>?, imageConfiguration: ImageConfiguration?) {
    val adapter = recyclerView.adapter as? PopularMoviesAdapter
    adapter?.apply {
        if(list != null && imageConfiguration != null) {
            items = list
            this.imageConfiguration = imageConfiguration
            notifyDataSetChanged()
        }
    }
}

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: PopularMoviesAdapter){
    recyclerView.adapter = adapter
}

@BindingAdapter(value = ["setImage", "setImageConfiguration"])
fun setImage(imageView: ImageView, path: String, imageConfiguration: ImageConfiguration?){
    val urlPath = "${imageConfiguration?.baseUrl}${imageConfiguration?.imageSizes?.lastOrNull()}$path"
    val uri = Uri.parse(urlPath)
    Glide.with(imageView.context)
        .load(uri)
        .placeholder(R.drawable.ic_film_placeholder)
        .error(R.drawable.ic_film_placeholder)
        .into(imageView)
}

@BindingAdapter("setSimpleDateText")
fun setSimpleDateText(textView: TextView, date: Date){
    textView.text = getDateText(date, "yyyy")
}

@BindingAdapter("setDetailedDateText")
fun setDetailedDateText(textView: TextView, date: Date){
    textView.text = getDateText(date, "yyyy. mm. dd.")
}

@BindingAdapter("android:text")
fun setFloat(view: TextView, value: Float) {
    view.text = "$value"
}

private fun getDateText(date: Date, format: String): String{
    val dateFormat = SimpleDateFormat(format)
    return dateFormat.format(date)
}