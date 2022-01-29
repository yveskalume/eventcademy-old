package com.yvkalume.domain.entity

import java.util.*
import kotlin.collections.ArrayList

data class Event(
    val uid: String = "",
    val title: String = "",
    val description: String = "",
    val attendeesUids: ArrayList<String> = arrayListOf(),
    val location: String = "",
    val link: String = "",
    val imageUrl: String = "",
    val offline: Boolean = true,
    val startDate: Date? = null,
    val endDate: Date? = null,
    val longitude: Long = 0,
    val latitude: Long = 0,
    val createdAt: Date? = null,
    val creatorUid: String = ""
)
