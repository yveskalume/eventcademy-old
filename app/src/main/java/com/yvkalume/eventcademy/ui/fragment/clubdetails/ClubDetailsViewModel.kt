package com.yvkalume.eventcademy.ui.fragment.clubdetails

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.yvkalume.data.mapper.EventUiMapper
import com.yvkalume.domain.usecase.club.GetEventsByClubUidUseCase
import com.yvkalume.eventcademy.di.mavericks.AssistedViewModelFactory
import com.yvkalume.eventcademy.di.mavericks.hiltMavericksViewModelFactory
import com.yvkalume.util.data
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.map

class ClubDetailsViewModel @AssistedInject constructor (
        @Assisted state: ClubDetailsViewState,
        private val getEventsByClubUidUseCase: GetEventsByClubUidUseCase
) : MavericksViewModel<ClubDetailsViewState>(state) {


    fun getClubEvents(uid: String) {
        getEventsByClubUidUseCase(uid).map {
            it.data!!.map { event -> EventUiMapper().map(event) }
        }.execute {
            copy(events= it)
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<ClubDetailsViewModel, ClubDetailsViewState> {
        override fun create(state: ClubDetailsViewState): ClubDetailsViewModel
    }

    companion object : MavericksViewModelFactory<ClubDetailsViewModel, ClubDetailsViewState> by hiltMavericksViewModelFactory()
}