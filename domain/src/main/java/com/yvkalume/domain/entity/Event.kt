package com.yvkalume.domain.entity

import java.util.*

data class Event(
    val uid: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val offline: Boolean,
    val date: Date? = null,
    val createdAt: Date? = null
)
