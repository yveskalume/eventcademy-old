package com.yvkalume.eventcademy.ui.fragment.event

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.yvkalume.domain.entity.User
import com.yvkalume.domain.usecase.event.CheckIfUserIsAttendingUseCase
import com.yvkalume.domain.usecase.event.GetAttendeesUseCase
import com.yvkalume.domain.usecase.user.SetHasGoingToAnEventUseCase
import com.yvkalume.eventcademy.di.mavericks.AssistedViewModelFactory
import com.yvkalume.eventcademy.di.mavericks.hiltMavericksViewModelFactory
import com.yvkalume.util.data
import com.yvkalume.util.succeeded
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class EventFragmentViewModel @AssistedInject constructor(
        @Assisted state: EventViewState,
        private val getAttendeesUseCase: GetAttendeesUseCase,
        private val setHasGoingToAnEventUseCase: SetHasGoingToAnEventUseCase,
        private val checkIfUserIsAttendingUseCase: CheckIfUserIsAttendingUseCase
) : MavericksViewModel<EventViewState>(state) {

    fun attend(user: User, eventUid: String) = viewModelScope.launch {
        setHasGoingToAnEventUseCase(Pair(user,eventUid))
    }

    fun checkIfUserIsAttending(userUid: String,eventUid: String) = viewModelScope.launch {
        checkIfUserIsAttendingUseCase(Pair(userUid,eventUid)).map {
            it.data!!
        }.execute {
            copy(isAttending = it)
        }
    }

    fun getAttendees(eventUid: String) = viewModelScope.launch {
        getAttendeesUseCase(eventUid).map {
            it.data!!
        }.execute {
            copy(attendees = it)
        }
    }

//    fun isAttendee(eventUid: String,user: User) = viewModelScope.launch {
//        getAttendees()
//    }


    @AssistedFactory
    interface Factory : AssistedViewModelFactory<EventFragmentViewModel, EventViewState> {
        override fun create(state: EventViewState): EventFragmentViewModel
    }

    companion object : MavericksViewModelFactory<EventFragmentViewModel, EventViewState> by hiltMavericksViewModelFactory()
}