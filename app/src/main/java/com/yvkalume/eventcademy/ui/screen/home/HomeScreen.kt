package com.yvkalume.eventcademy.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yvkalume.eventcademy.app.navigation.Screen
import com.yvkalume.eventcademy.ui.screen.home.components.EventHorizontalItem
import com.yvkalume.eventcademy.ui.screen.home.components.EventVerticalItem
import com.yvkalume.eventcademy.ui.screen.home.components.PopularEventHeader
import com.yvkalume.eventcademy.ui.screen.home.components.UpcomingEventHeader

@Composable
fun HomeScreen(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            UpcomingEventHeader()
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                EventVerticalItem(modifier = Modifier.weight(1f).clickable { navController.navigate(Screen.EventDetails.route) })
                EventVerticalItem(modifier = Modifier.weight(1f).clickable { navController.navigate(Screen.EventDetails.route) })
            }
        }

        item {
            PopularEventHeader()
        }

        items(10) {
            EventHorizontalItem(onClick = { navController.navigate(Screen.EventDetails.route) })
        }
    }
}