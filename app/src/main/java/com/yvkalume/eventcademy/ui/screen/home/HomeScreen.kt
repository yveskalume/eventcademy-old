package com.yvkalume.eventcademy.ui.screen.home

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
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
import com.yvkalume.eventcademy.ui.screen.home.business.HomeData
import com.yvkalume.eventcademy.ui.screen.home.business.HomeViewModel
import com.yvkalume.eventcademy.ui.screen.home.business.HomeViewState
import com.yvkalume.eventcademy.ui.screen.home.components.*
import com.yvkalume.eventcademy.ui.sharedcomponents.EmptyContentScreen
import com.yvkalume.eventcademy.ui.sharedcomponents.Toast

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = mavericksViewModel()) {

    val context = LocalContext.current
    val state by viewModel.collectAsState(HomeViewState::data)

    var searchText by remember {
        mutableStateOf("")
    }

    Crossfade(targetState = state) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                HomeTopBar(modifier = Modifier.fillMaxWidth(), onClick = {})
            }


            when (it) {
                is Uninitialized -> {
                    Log.e("HomeScreen", "Uni")
                }
                is Loading -> {
                    Log.e("HomeScreen", "Loading")
                }
                is Success -> {
                    Log.e("HomeScreen", it.invoke().toString())
                    HomeContent(
                        data = it.invoke()
                    ) { navController.navigate(Screen.EventDetails.route) }
                }
                is Fail -> {
                    Toast(context = context, message = "Connexion impossible")
                }
            }

        }

    }

}

private fun LazyListScope.HomeContent(
    data: HomeData,
    onEventItemClicked: () -> Unit
) {

    if (data.upComingEvents.isEmpty()) {
        item {
            EmptyContentScreen()
        }
    }

    item {
        if (data.upComingEvents.isNotEmpty()) {
            UpcomingEventHeader()
        }
    }

    item {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            if (data.upComingEvents.isNotEmpty()) {
                EventVerticalItem(
                    event = data.upComingEvents[0],
                    modifier = Modifier
                        .weight(1f)
                        .clickable(onClick = onEventItemClicked)
                )
            }

            if (data.upComingEvents.size >= 2) {
                EventVerticalItem(
                    event = data.upComingEvents[1],
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

    items(data.upComingEvents) { event ->
        EventHorizontalItem(event = event,onClick = onEventItemClicked)
    }
}