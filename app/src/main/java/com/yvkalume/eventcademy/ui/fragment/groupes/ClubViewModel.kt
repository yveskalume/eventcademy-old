package com.yvkalume.eventcademy.ui.fragment.groupes

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.yvkalume.domain.usecase.club.GetAllClubUseCase
import com.yvkalume.eventcademy.di.mavericks.AssistedViewModelFactory
import com.yvkalume.eventcademy.di.mavericks.hiltMavericksViewModelFactory
import com.yvkalume.util.data
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.map

class ClubViewModel @AssistedInject constructor (@Assisted state: ClubViewState, private val getAllClubUseCase: GetAllClubUseCase) : MavericksViewModel<ClubViewState>(state) {
    init {
        getAllClubs()
    }

    private fun getAllClubs() {
        getAllClubUseCase(Unit).map {
            it.data!!
        }.execute {
            copy(clubs = it)
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<ClubViewModel, ClubViewState> {
        override fun create(state: ClubViewState): ClubViewModel
    }

    companion object : MavericksViewModelFactory<ClubViewModel,ClubViewState> by hiltMavericksViewModelFactory()
}