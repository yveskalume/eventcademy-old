package com.yvkalume.eventcademy.ui.screen.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun EventHorizontalItem() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        Image(
            painter = painterResource(id = R.drawable.splash_img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .width(174.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(text = "Oct 12,2021", maxLines = 1)
            Text(
                text = "Tour of the Alps",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            )
            Text(
                text = "Biggest Event in dhaka,join to see the excitment of the new car guy",
                maxLines = 2
            )
        }
    }
}