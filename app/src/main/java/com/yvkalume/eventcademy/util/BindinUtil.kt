package com.yvkalume.eventcademy.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["setImageUrl"], requireAll = false)
fun ImageView.setImageUrl(url: String?) {
    if (url != null && url.isNotBlank()) {
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions().placeholder(android.R.color.darker_gray))
            .into(this)
    }
}