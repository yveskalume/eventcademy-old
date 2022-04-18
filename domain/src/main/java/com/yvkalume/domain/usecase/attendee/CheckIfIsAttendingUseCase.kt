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

package com.yvkalume.domain.usecase.attendee

import com.yvkalume.domain.repository.AttendeeRepository
import com.yvkalume.domain.usecase.attendee.CheckIfIsAttendingUseCase.CheckIfIsAttendingParams
import com.yvkalume.domain.util.CoroutineUseCase
import com.yvkalume.domain.util.FlowUseCase
import com.yvkalume.util.Result
import com.yvkalume.util.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckIfIsAttendingUseCase @Inject constructor(
    private val repository: AttendeeRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : CoroutineUseCase<CheckIfIsAttendingParams, Boolean>(dispatcher) {

    data class CheckIfIsAttendingParams(val eventUid: String, val userUid: String)

    override suspend fun execute(params: CheckIfIsAttendingParams): Boolean {
        return repository.checkIfIsAttending("${params.userUid}-${params.eventUid}")
    }
}