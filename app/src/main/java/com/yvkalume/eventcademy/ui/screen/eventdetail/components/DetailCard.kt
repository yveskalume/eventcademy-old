package com.yvkalume.eventcademy.ui.screen.eventdetail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailCard(modifier: Modifier) {
    Card(modifier = modifier, elevation = 4.dp, shape = RoundedCornerShape(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colors.primary,
                contentColor = Color.White,
                modifier = Modifier.size(60.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Jul")
                    Text(text = "06", fontSize = 18.sp)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Basket Game", fontWeight = FontWeight.Bold)
                Text(
                    text = "Ven - De 08:30 à 09:30",
                    fontSize = 12.sp
                )

                Text(
                    text = "Lubumbashi",
                    fontSize = 12.sp
                )
            }
        }
    }
}