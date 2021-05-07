package com.yvkalume.eventcademy.ui.fragment.groupes

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.yvkalume.domain.entity.Club

data class ClubViewState(
        val clubs : Async<List<Club>> = Uninitialized
) : MavericksState