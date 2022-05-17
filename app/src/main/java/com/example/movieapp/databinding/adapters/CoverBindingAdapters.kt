package com.example.movieapp.databinding.adapters

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.example.movieapp.models.state.*

@BindingAdapter("setCoverVisibility")
fun setCoverVisibility(view: View, viewState: ViewState){
    view.isVisible = viewState !is Content
}

@BindingAdapter("setEmptyVisibility")
fun setEmptyVisibility(textView: TextView, viewState: ViewState){
    textView.isVisible = viewState is Empty
}

@BindingAdapter("setErrorVisibility")
fun setErrorVisibility(view: View, viewState: ViewState){
    view.isVisible = viewState is Error
}

@BindingAdapter("setProgressVisibility")
fun setProgressVisibility(progressBar: ProgressBar, viewState: ViewState){
    progressBar.isVisible = viewState is Loading
}