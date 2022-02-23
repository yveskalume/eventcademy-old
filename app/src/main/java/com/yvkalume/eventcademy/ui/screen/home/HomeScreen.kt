package com.yvkalume.eventcademy.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.yvkalume.eventcademy.app.navigation.Screen
import com.yvkalume.eventcademy.ui.screen.home.components.*
import com.yvkalume.eventcademy.ui.sharedcomponents.SearchTextField

@Composable
fun HomeScreen(navController: NavHostController) {
    var searchText by remember {
        mutableStateOf("")
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            HomeTopBar(modifier = Modifier.fillMaxWidth(),onClick = {})
        }
        item {
            UpcomingEventHeader()
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                EventVerticalItem(modifier = Modifier
                    .weight(1f)
                    .clickable { navController.navigate(Screen.EventDetails.route) })
                EventVerticalItem(modifier = Modifier
                    .weight(1f)
                    .clickable { navController.navigate(Screen.EventDetails.route) })
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

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}