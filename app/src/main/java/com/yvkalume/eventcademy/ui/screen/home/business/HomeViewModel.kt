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

package com.yvkalume.eventcademy.ui.screen.home.business

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.yvkalume.domain.usecase.event.GetComingEventUseCase
import com.yvkalume.eventcademy.app.di.mavericks.AssistedViewModelFactory
import com.yvkalume.eventcademy.app.di.mavericks.hiltMavericksViewModelFactory
import com.yvkalume.util.data
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel @AssistedInject constructor(
    @Assisted state: HomeViewState,
    private val getComingEventUseCase: GetComingEventUseCase
) : MavericksViewModel<HomeViewState>(state) {

    init {
        getComingAndPopularEvents()
    }

    private fun getComingAndPopularEvents() = viewModelScope.launch {
        val upComingFlow = getComingEventUseCase(Unit).map { it.data!! }

        // FIXME: replace second upcoming by flow from getPopularEventsUseCase
        combine(upComingFlow,upComingFlow) { upComing, _ ->
            HomeData(upComingEvents = upComing)
        }.execute {
            copy(data = it)
        }

    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<HomeViewModel, HomeViewState> {
        override fun create(state: HomeViewState): HomeViewModel
    }

    companion object : MavericksViewModelFactory<HomeViewModel, HomeViewState> by hiltMavericksViewModelFactory()
}