package com.yvkalume.eventcademy.ui.fragment.event

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.google.firebase.firestore.auth.User

data class EventViewState (
    val attendees: Async<List<User>> = Uninitialized
) : MavericksState