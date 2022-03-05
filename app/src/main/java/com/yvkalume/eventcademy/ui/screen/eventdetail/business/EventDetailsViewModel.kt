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
import com.yvkalume.domain.usecase.event.GetOneEventByUidUseCase
import com.yvkalume.eventcademy.app.di.mavericks.AssistedViewModelFactory
import com.yvkalume.eventcademy.app.di.mavericks.hiltMavericksViewModelFactory
import com.yvkalume.util.data
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class EventDetailsViewModel @AssistedInject constructor(
    @Assisted state: EventDetailsViewState,
    private val getOneEventByUidUseCase: GetOneEventByUidUseCase
) : MavericksViewModel<EventDetailsViewState>(state) {

    fun getEventDetails(eventUid: String) {
        val eventFlow = getOneEventByUidUseCase(eventUid).map { it.data!! }
        combine(eventFlow, eventFlow) { event, _ ->
            EventDetailsData(event)
        }.execute {
            copy(data = it)
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<EventDetailsViewModel, EventDetailsViewState> {
        override fun create(state: EventDetailsViewState): EventDetailsViewModel
    }

    companion object :
        MavericksViewModelFactory<EventDetailsViewModel, EventDetailsViewState> by hiltMavericksViewModelFactory()
}