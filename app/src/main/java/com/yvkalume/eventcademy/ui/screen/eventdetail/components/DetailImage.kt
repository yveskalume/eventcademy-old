package com.yvkalume.eventcademy.ui.screen.eventdetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yvkalume.eventcademy.R

@Composable
fun DetailImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.splash_img),
        contentDescription = null,
        modifier = Modifier.height(300.dp).clip(RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp)).then(modifier),
        contentScale = ContentScale.Crop
    )
}