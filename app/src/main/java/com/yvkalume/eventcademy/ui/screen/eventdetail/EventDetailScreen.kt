package com.yvkalume.eventcademy.ui.screen.eventdetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.yvkalume.eventcademy.ui.screen.eventdetail.business.EventDetailsData
import com.yvkalume.eventcademy.ui.screen.eventdetail.business.EventDetailsViewModel
import com.yvkalume.eventcademy.ui.screen.eventdetail.business.EventDetailsViewState
import com.yvkalume.eventcademy.ui.screen.eventdetail.components.*
import com.yvkalume.eventcademy.ui.sharedcomponents.Toast
import com.yvkalume.util.orFalse

@Composable
fun EventDetailScreen(
    eventUid: String,
    navController: NavHostController,
    viewModel: EventDetailsViewModel = mavericksViewModel()
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val eventData by viewModel.collectAsState(EventDetailsViewState::data)
    val isAttendingState by viewModel.collectAsState(EventDetailsViewState::isAttending)
    var isAttending by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel) {
        viewModel.getEventDetails(eventUid = eventUid)
        viewModel.checkIfUserIsAttending(eventUid = eventUid)
    }

    LaunchedEffect(isAttendingState) {
        if(isAttendingState is Success) {
            isAttending = isAttendingState.invoke().orFalse
        }
    }

    Crossfade(
        targetState = eventData,
        modifier = Modifier.scrollable(state = scrollState, orientation = Orientation.Vertical)

    ) {
        when (it) {
            is Uninitialized -> {}
            is Loading -> {}
            is Success -> {
                EventDetailsContent(
                    data = it.invoke(),
                    isAttending = isAttendingState.invoke().orFalse,
                    attendLoading = isAttendingState is Loading,
                    onAttendButtonClick = {
                        viewModel.attendeeToAnEvent(event = it.invoke().event)
                    })
            }
            is Fail -> {

            }
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun EventDetailsContent(
    data: EventDetailsData,
    isAttending: Boolean,
    attendLoading: Boolean,
    onAttendButtonClick: () -> Unit
) {

    val scrollState = rememberScrollState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .scrollable(state = scrollState, orientation = Orientation.Vertical)
            ) {
                val (card, image) = createRefs()

                DetailImage(imageUrl = data.event.imageUrl, modifier = Modifier.constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
                DetailCard(event = data.event, modifier = Modifier
                    .height(80.dp)
                    .constrainAs(card) {
                        top.linkTo(image.bottom)
                        bottom.linkTo(image.bottom)
                        start.linkTo(parent.start, 24.dp)
                        end.linkTo(parent.end, 24.dp)
                        width = Dimension.fillToConstraints
                    })
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                DetailText(description = data.event.description)
                Spacer(modifier = Modifier.height(16.dp))
                DetailHost()
            }
        }

        item {
            AnimatedVisibility(visible = isAttending) {
                DetailAttendees(attendees = data.attendees, onSeeMoreClick = {})
            }
        }
        item {
            Button(
                onClick = onAttendButtonClick,
                enabled = !isAttending,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Text(text = "J'y vais !", style = MaterialTheme.typography.button)
//                AnimatedVisibility(visible = attendLoading) {
//                    CircularProgressIndicator(
//                        modifier = Modifier
//                            .size(20.dp)
//                            .padding(start = 2.dp),
//                        color = MaterialTheme.colors.background
//                    )
//                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}