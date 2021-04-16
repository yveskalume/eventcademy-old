package com.yvkalume.eventcademy.ui.fragment.home

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.yvkalume.data.mapper.EventUiMapper
import com.yvkalume.data.model.presentation.HomeData
import com.yvkalume.domain.usecase.event.GetAllEventUseCase
import com.yvkalume.domain.usecase.event.GetOfflineEvents
import com.yvkalume.domain.usecase.event.GetOnlineEvents
import com.yvkalume.eventcademy.di.mavericks.AssistedViewModelFactory
import com.yvkalume.eventcademy.di.mavericks.hiltMavericksViewModelFactory
import com.yvkalume.util.data
import com.yvkalume.util.succeeded
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel @AssistedInject constructor(
    @Assisted state: HomeViewState,
    private val getOnlineEvents: GetOnlineEvents,
    private val getOfflineEvents: GetOfflineEvents
) : MavericksViewModel<HomeViewState>(state) {


    private fun getAllEvents() = viewModelScope.launch {
        val onlineEvents = getOnlineEvents(Unit).map {
            it.data?.map { event ->
                EventUiMapper().map(event)
            }
        }

        val offlineEvents = getOfflineEvents(Unit).map {
            it.data?.map { event ->
                EventUiMapper().map(event)
            }
        }
        combine(onlineEvents,offlineEvents) { online, offline ->
            HomeData(online,offline)

        }.execute {
            copy(data = it)
        }
    }


    @AssistedFactory
    interface Factory : AssistedViewModelFactory<HomeViewModel,HomeViewState> {
        override fun create(state: HomeViewState): HomeViewModel
    }

    companion object : MavericksViewModelFactory<HomeViewModel,HomeViewState> by hiltMavericksViewModelFactory()
}