package com.yvkalume.domain.entity

import java.util.*

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String="",
    val profilUrl: String="",
    val createdAt: Date? = null
)