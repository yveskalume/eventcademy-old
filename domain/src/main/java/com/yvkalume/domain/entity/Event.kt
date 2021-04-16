package com.yvkalume.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

data class Event(
    val uid: String = "",
    val title: String = "",
    val description: String = "",
    val lieu: String = "",
    val imageUrl: String = "",
    val offline: Boolean = true,
    val date: Date? = null,
    val createdAt: Date? = null
)
