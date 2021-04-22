package com.yvkalume.eventcademy.util

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter(value = ["setImageUrl"], requireAll = false)
fun ImageView.setImageUrl(url: String?) {
    if (url != null && url.isNotBlank()) {
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions().placeholder(android.R.color.darker_gray))
            .into(this)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["urlText"])
fun TextView.setUrlText(value: String?) {
    text = when {
        !value.isNullOrBlank() && !value.startsWith("http://") && !value.startsWith("https://") -> "http://$value"
        value.isNullOrBlank() -> "https://espacesis.org"
        else -> value
    }

    setOnClickListener {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(text.toString()))
        startActivity(this.context,browserIntent,null)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("humanDate")
fun TextView.sethumanDate(eventDate: Date){
    val date = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(eventDate)
    val time = SimpleDateFormat("HH:mm", Locale.FRANCE).format(eventDate)
    text = "$date à $time"
}