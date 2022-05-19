package com.example.movieapp.databinding.adapters

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.models.Genre
import com.example.movieapp.models.ImageConfiguration
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter(value = ["setImage", "setImageConfiguration"])
fun setImage(imageView: ImageView, path: String?, imageConfiguration: ImageConfiguration?){
    val uri: Uri? = getImageUri(path, imageConfiguration)
    Glide.with(imageView.context)
        .load(uri)
        .placeholder(R.drawable.ic_film_placeholder)
        .error(R.drawable.ic_film_placeholder)
        .into(imageView)
}

@BindingAdapter("setSimpleDateText")
fun setSimpleDateText(textView: TextView, date: Date?){
    setDateText(date, textView, "yyyy")
}

@BindingAdapter("setDetailedDateText")
fun setDetailedDateText(textView: TextView, date: Date?){
    setDateText(date, textView, "yyyy. MM. dd.")
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

private fun setDateText(date: Date?, textView: TextView, format: String) {
    if (date != null) {
        textView.isVisible = true
        textView.text = getDateText(date, format)
    } else {
        textView.isVisible = false
    }
}

private fun getDateText(date: Date, format: String): String{
    val dateFormat = SimpleDateFormat(format)
    return dateFormat.format(date)
}

private fun getImageUri(
    path: String?,
    imageConfiguration: ImageConfiguration?
): Uri? {
    var uri: Uri? = null
    if (path != null && imageConfiguration != null) {
        val urlPath =
            "${imageConfiguration.baseUrl}${imageConfiguration.imageSizes.lastOrNull()}$path"
        uri = Uri.parse(urlPath)
    }
    return uri
}