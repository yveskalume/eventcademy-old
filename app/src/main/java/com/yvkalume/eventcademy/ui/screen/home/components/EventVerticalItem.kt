package com.yvkalume.eventcademy.ui.screen.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yvkalume.eventcademy.R

@Composable
fun EventVerticalItem(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.splash_img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(174.dp, 147.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Text(text = "Oct 12,2021", maxLines = 1, fontSize = 12.sp)
        Text(
            text = "Tour of the Alps",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        )
    }
}