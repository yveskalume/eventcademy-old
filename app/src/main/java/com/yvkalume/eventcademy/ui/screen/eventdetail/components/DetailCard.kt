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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yvkalume.domain.entity.Event
import com.yvkalume.util.DateUtil
import com.yvkalume.util.humanMonth
import com.yvkalume.util.humanMonthDay

@Composable
fun DetailCard(event: Event, modifier: Modifier) {
    Card(modifier = modifier, elevation = 4.dp, shape = RoundedCornerShape(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
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
                    Text(
                        text = event.startDate?.humanMonth.toString(), overflow =
                        TextOverflow.Clip
                    )
                    Text(text = event.startDate?.humanMonthDay.toString(), fontSize = 18.sp)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = event.title, fontWeight = FontWeight.Bold)
                Text(
                    text = DateUtil.fromTo(event.startDate, event.endDate),
                    fontSize = 12.sp
                )

                Text(
                    text = event.location,
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}