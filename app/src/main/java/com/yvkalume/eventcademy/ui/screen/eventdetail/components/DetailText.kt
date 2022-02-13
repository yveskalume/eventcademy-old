package com.yvkalume.eventcademy.ui.screen.eventdetail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DetailText() {
    Text(text = "Description", fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Biggest Event in dhaka,join to see the excitment of the new car guy. Biggest Event in dhaka,join to see the excitment of the new car guy",
        modifier = Modifier.fillMaxWidth()
    )
}