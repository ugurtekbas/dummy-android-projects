package com.example.newgithubuser.presentation.ui.utils

import android.widget.ImageView

interface ImageLoader {
    fun loadAndFitImage(imageUrl: String, imageView: ImageView, placeholderResource: Int)
}
