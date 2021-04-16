package com.yvkalume.data.model

import java.util.*

data class EventUiModel (
    val uid: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val date: Date? = null,
    val createdAt: Date? = null
)