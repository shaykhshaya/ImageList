package com.shaya.fliptree.utils

import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaya.fliptree.response.ImageItem
import com.shaya.fliptree.ui.adapter.ImageListAdapter
import com.shaya.fliptree.ui.viewmodel.ImageApiStatus

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String) {
    imgView.loadImageByUrl(imgView.context, imgUrl)
}


@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<ImageItem>?
) {
    val adapter = recyclerView.adapter as ImageListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageApiStatus", "errorImageView", requireAll = true)
fun bindStatus(
    progressBar: ProgressBar,
    status: ImageApiStatus,
    errorImageView: ImageView?,
) {
    when (status) {
        ImageApiStatus.LOADING -> {
            progressBar.visible(true)
            errorImageView?.visible(false)
        }
        ImageApiStatus.ERROR -> {
            progressBar.visible(false)
            errorImageView?.visible(true)
        }
        ImageApiStatus.DONE -> {
            progressBar.visible(false)
            errorImageView?.visible(false)
        }
    }
}