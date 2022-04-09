/*
 * Copyright (c) 2022 EventCademy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yvkalume.eventcademy.ui.screen.eventdetail.business

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.yvkalume.domain.entity.Attendee
import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.usecase.attendee.AttendeeToAnEventUseCase
import com.yvkalume.domain.usecase.attendee.CheckIfIsAttendingUseCase
import com.yvkalume.domain.usecase.attendee.CheckIfIsAttendingUseCase.CheckIfIsAttendingParams
import com.yvkalume.domain.usecase.attendee.GetAttendeesByEventUidUseCase
import com.yvkalume.domain.usecase.event.GetOneEventByUidUseCase
import com.yvkalume.eventcademy.app.di.mavericks.AssistedViewModelFactory
import com.yvkalume.eventcademy.app.di.mavericks.hiltMavericksViewModelFactory
import com.yvkalume.util.data
import com.yvkalume.util.orFalse
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class EventDetailsViewModel @AssistedInject constructor(
    @Assisted state: EventDetailsViewState,
    private val getOneEventByUidUseCase: GetOneEventByUidUseCase,
    private val attendeeToAnEventUseCase: AttendeeToAnEventUseCase,
    private val checkIfIsAttendingUseCase: CheckIfIsAttendingUseCase,
    private val getAttendeesByEventUidUseCase: GetAttendeesByEventUidUseCase,
    private val firebaseAuth: FirebaseAuth
) : MavericksViewModel<EventDetailsViewState>(state) {

    private val user by lazy {
        firebaseAuth.currentUser
    }

    fun getEventDetails(eventUid: String) = viewModelScope.launch {
        val eventFlow = getOneEventByUidUseCase(eventUid).map { it.data!! }
        val attendeesFlow = getAttendeesByEventUidUseCase(eventUid).map { it.data!! }
        combine(eventFlow, attendeesFlow) { event, attendees ->
            EventDetailsData(event = event, attendees = attendees)
        }.execute {
            copy(data = it)
        }

    }

    fun checkIfUserIsAttending(eventUid: String) = viewModelScope.launch {
       checkIfIsAttendingUseCase(
            CheckIfIsAttendingParams(
                eventUid = eventUid,
                userUid = user?.uid.toString()
            )
        ).map { it.data!! }.execute {
            copy(isAttending = it)
        }
    }

    fun attendeeToAnEvent(event: Event) = viewModelScope.launch {
        val attendee = Attendee(
            userUid = user?.uid.toString(),
            userName = user?.displayName.toString(),
            userProfile = user?.photoUrl.toString(),
            eventUid = event.uid,
            eventTitle = event.title
        )
        attendeeToAnEventUseCase(attendee)
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<EventDetailsViewModel, EventDetailsViewState> {
        override fun create(state: EventDetailsViewState): EventDetailsViewModel
    }

    companion object :
        MavericksViewModelFactory<EventDetailsViewModel, EventDetailsViewState> by hiltMavericksViewModelFactory()
}