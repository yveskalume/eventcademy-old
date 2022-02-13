package com.yvkalume.eventcademy.ui.screen.eventdetail

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.yvkalume.eventcademy.ui.screen.eventdetail.components.*

@Composable
fun EventDetailScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize().scrollable(state = scrollState,orientation = Orientation.Vertical)) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val (card, image) = createRefs()

            DetailImage(modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
            DetailCard(modifier = Modifier
                .height(80.dp)
                .constrainAs(card) {
                    top.linkTo(image.bottom)
                    bottom.linkTo(image.bottom)
                    start.linkTo(parent.start, 24.dp)
                    end.linkTo(parent.end, 24.dp)
                    width = Dimension.fillToConstraints
                })
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)) {
            DetailText()
            Spacer(modifier = Modifier.height(16.dp))
            DetailHost()
        }
        DetailAttendees(onSeeMoreClick = {})
    }
}