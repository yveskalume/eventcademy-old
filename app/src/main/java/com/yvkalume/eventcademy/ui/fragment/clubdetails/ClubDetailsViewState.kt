package com.yvkalume.eventcademy.ui.fragment.clubdetails

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.yvkalume.data.model.EventUiModel

data class ClubDetailsViewState(
    val events : Async<List<EventUiModel>> = Uninitialized
) : MavericksState