package com.yvkalume.eventcademy.ui.screen.home.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun PopularEventHeader() {
    Text(text = "Populaires", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
}