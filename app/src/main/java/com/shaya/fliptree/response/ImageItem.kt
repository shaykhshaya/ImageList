package com.shaya.fliptree.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageItem(
    val title: String?,
    val image: String?
) : Parcelable
