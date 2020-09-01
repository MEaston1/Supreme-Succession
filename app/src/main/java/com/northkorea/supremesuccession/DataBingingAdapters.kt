package com.northkorea.supremesuccession

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object DataBingingAdapters {
    @BindingAdapter("android:src")
    fun setImageResoruce(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }
}