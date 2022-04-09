package com.yvkalume.eventcademy.ui.sharedcomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
fun UserSquareAvatar(imageUrl: String, size: Dp, onClick: () -> Unit = {}) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentScale = ContentScale.Crop,
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = null,
        modifier = Modifier
            .size(size = size)
            .clip(
                RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
    )
}