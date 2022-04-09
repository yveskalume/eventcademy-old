package com.yvkalume.eventcademy.ui.screen.eventdetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yvkalume.domain.entity.Attendee
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.ui.sharedcomponents.UserSquareAvatar

@Composable
fun DetailAttendees(attendees: List<Attendee>,onSeeMoreClick: () -> Unit) {
    Column(modifier = Modifier.wrapContentHeight()) {
        Text(
            text = "Participants",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
        ) {
            items(attendees) { attendee ->
                UserSquareAvatar(imageUrl = attendee.userProfile, size = 60.dp)
            }
            item {
                Box(
                    modifier = Modifier
                        .size(size = 60.dp).clip(RoundedCornerShape(16.dp))
                        .clickable(onClick = onSeeMoreClick),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "+10", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}