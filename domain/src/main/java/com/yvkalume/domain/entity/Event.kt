package com.yvkalume.domain.entity

import java.util.*

data class Event(
    val uid: String = "",
    val title: String = "",
    val description: String = "",
    val lieu: String = "",
    val link: String = "",
    val imageUrl: String = "",
    val offline: Boolean = true,
    val date: Date? = null,
    val createdAt: Date? = null,
    val clubUid: String = ""
)
