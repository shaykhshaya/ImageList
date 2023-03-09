package com.shaya.fliptree.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.shaya.fliptree.R

fun ImageView.loadImageByUrl(context: Context, url: String){
    Glide
        .with(context)
        .load(url)
        .fitCenter()
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_connection_error)
        .into(this)
}

fun View.visible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}