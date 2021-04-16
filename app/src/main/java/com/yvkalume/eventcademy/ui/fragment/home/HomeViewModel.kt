package com.yvkalume.eventcademy.ui.fragment.home

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.yvkalume.data.mapper.EventUiMapper
import com.yvkalume.data.model.presentation.HomeData
import com.yvkalume.domain.usecase.event.GetNextEventUseCase
import com.yvkalume.domain.usecase.event.GetOfflineEventsUseCase
import com.yvkalume.domain.usecase.event.GetOnlineEventsUseCase
import com.yvkalume.eventcademy.di.mavericks.AssistedViewModelFactory
import com.yvkalume.eventcademy.di.mavericks.hiltMavericksViewModelFactory
import com.yvkalume.util.data
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Singleton

class HomeViewModel @AssistedInject constructor (
    @Assisted state: HomeViewState,
    private val getOnlineEvents: GetOnlineEventsUseCase,
    private val getOfflineEvents: GetOfflineEventsUseCase,
    private val getNextEventUseCase: GetNextEventUseCase
) : MavericksViewModel<HomeViewState>(state) {


    init {
        getAllEvents()
    }

    private fun getAllEvents() = viewModelScope.launch {
        val nextEvent =  getNextEventUseCase(Unit).map {
            it.data?.let { it1 -> EventUiMapper().map(it1) }
        }
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
        combine(nextEvent,onlineEvents,offlineEvents) { next , online, offline ->
            HomeData(next,online,offline)

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