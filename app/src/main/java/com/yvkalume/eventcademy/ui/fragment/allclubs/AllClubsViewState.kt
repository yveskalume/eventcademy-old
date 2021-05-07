package com.yvkalume.eventcademy.ui.fragment.allclubs

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.yvkalume.data.model.ClubUiModel

data class AllClubsViewState(
        val clubs : Async<List<ClubUiModel>> = Uninitialized
) : MavericksState