package com.yvkalume.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class EventUiModel (
    val uid: String = "",
    val title: String = "",
    val description: String = "",
    val lieu: String = "",
    val link: String = "",
    val imageUrl: String = "",
    val date: Date? = null,
    val createdAt: Date? = null
) : Parcelable