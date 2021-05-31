package com.example.newgithubuser.presentation.ui.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.newgithubuser.di.ActivityScope
import javax.inject.Inject

@ActivityScope
class ImageService @Inject constructor(
    private val context: Context
): ImageLoader {

    override fun loadAndFitImage(imageUrl: String, imageView: ImageView, placeholderResource: Int) {
        Glide.with(context)
            .load(imageUrl)
            .fitCenter()
            .placeholder(placeholderResource)
            .error(placeholderResource)
            .into(imageView)
    }
}
