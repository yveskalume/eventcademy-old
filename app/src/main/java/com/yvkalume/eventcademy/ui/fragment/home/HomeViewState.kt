package com.yvkalume.eventcademy.ui.fragment.home

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.yvkalume.data.model.presentation.HomeData

data class HomeViewState(
    val data: Async<HomeData> = Uninitialized
) : MavericksState