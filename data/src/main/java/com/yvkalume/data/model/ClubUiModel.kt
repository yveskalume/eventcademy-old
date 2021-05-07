package com.yvkalume.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClubUiModel(
        val uid: String = "",
        val title: String = "",
        val imageUrl: String = "",
        val description: String = ""
) : Parcelable