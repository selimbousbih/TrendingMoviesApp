package com.selim.components

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.selim.components.imageview.BaseImageView


@BindingAdapter("imageUrl")
fun BaseImageView.imageUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.color.darkGray)
        .fallback(R.color.lightGray)
        .into(this)
}

@BindingAdapter("imageUrl")
fun com.elyeproj.loaderviewlibrary.LoaderImageView.imageUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.color.darkGray)
        .fallback(R.color.lightGray)
        .into(this)
}
