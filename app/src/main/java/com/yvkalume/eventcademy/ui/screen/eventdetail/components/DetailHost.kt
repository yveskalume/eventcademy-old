package com.yvkalume.eventcademy.ui.screen.eventdetail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.ui.sharedcomponents.UserSquareAvatar

@Composable
fun DetailHost() {
    Text(text = "Organisateur", fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(8.dp))
    UserSquareAvatar(painter = painterResource(id = R.drawable.splash_img), size = 60.dp)
}