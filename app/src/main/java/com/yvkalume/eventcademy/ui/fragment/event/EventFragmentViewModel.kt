package com.yvkalume.eventcademy.ui.fragment.event

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.yvkalume.domain.entity.User
import com.yvkalume.domain.usecase.user.SetHasGoingToAnEventUseCase
import com.yvkalume.eventcademy.di.mavericks.AssistedViewModelFactory
import com.yvkalume.eventcademy.di.mavericks.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class EventFragmentViewModel @AssistedInject constructor(
        @Assisted state: EventViewState,
        private val setHasGoingToAnEventUseCase: SetHasGoingToAnEventUseCase
) : MavericksViewModel<EventViewState>(state) {


    fun attend(user: User, eventUid: String) = viewModelScope.launch {
        setHasGoingToAnEventUseCase(Pair(user,eventUid))
    }


    @AssistedFactory
    interface Factory : AssistedViewModelFactory<EventFragmentViewModel, EventViewState> {
        override fun create(state: EventViewState): EventFragmentViewModel
    }

    companion object : MavericksViewModelFactory<EventFragmentViewModel, EventViewState> by hiltMavericksViewModelFactory()
}