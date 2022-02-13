package com.yvkalume.eventcademy.ui.sharedcomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yvkalume.eventcademy.R

@Composable
fun UserSquareAvatar(painter: Painter,size: Dp, onClick: () -> Unit = {}) {
    Image(painter = painter, contentDescription = null, contentScale = ContentScale.Crop ,modifier = Modifier.size(size = size).clip(
        RoundedCornerShape(16.dp)).clickable(onClick = onClick))
}