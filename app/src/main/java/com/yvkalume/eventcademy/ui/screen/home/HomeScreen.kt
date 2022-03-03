package com.yvkalume.eventcademy.ui.screen.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.yvkalume.domain.entity.Event
import com.yvkalume.eventcademy.app.navigation.Screen
import com.yvkalume.eventcademy.ui.screen.home.business.HomeViewModel
import com.yvkalume.eventcademy.ui.screen.home.business.HomeViewState
import com.yvkalume.eventcademy.ui.screen.home.components.*
import com.yvkalume.eventcademy.ui.sharedcomponents.Toast

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = mavericksViewModel()) {

    val context = LocalContext.current
    val state by viewModel.collectAsState(HomeViewState::data)

    Crossfade(targetState = state) {
        when (it) {
            is Uninitialized -> {}
            is Loading -> {}
            is Success -> {
                HomeContent(
                    upComingEvents = it.invoke().upComingEvents,
                    onEventItemClicked = { navController.navigate(Screen.EventDetails.route) }
                )
            }
            is Fail -> {
                Toast(context = context, message = "Connexion impossible")
            }
        }
    }

}

@Composable
private fun HomeContent(
    upComingEvents: List<Event>,
    onEventItemClicked: () -> Unit
) {

    var searchText by remember {
        mutableStateOf("")
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            HomeTopBar(modifier = Modifier.fillMaxWidth(), onClick = {})
        }
        item {
            if (upComingEvents.isNotEmpty()) {
                UpcomingEventHeader()
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                if (upComingEvents.isNotEmpty()) {
                    EventVerticalItem(
                        event = upComingEvents[0],
                        modifier = Modifier
                            .weight(1f)
                            .clickable(onClick = onEventItemClicked)
                    )
                }

                if (upComingEvents.size >= 2) {
                    EventVerticalItem(
                        event = upComingEvents[1],
                        modifier = Modifier
                            .weight(1f)
                            .clickable(onClick = onEventItemClicked),
                    )
                }
            }
        }

        item {
            PopularEventHeader()
        }

        items(10) {
            EventHorizontalItem(onClick = onEventItemClicked)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}