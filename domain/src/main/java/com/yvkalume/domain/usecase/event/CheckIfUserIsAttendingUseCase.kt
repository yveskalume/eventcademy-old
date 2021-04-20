package com.yvkalume.domain.usecase.event

import com.yvkalume.domain.repository.EventRepository
import com.yvkalume.domain.util.FlowUseCase
import com.yvkalume.util.Result
import com.yvkalume.util.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckIfUserIsAttendingUseCase @Inject constructor (private val repository: EventRepository, @IoDispatcher private val dispatcher: CoroutineDispatcher) : FlowUseCase<Pair<String,String>,Boolean>(dispatcher) {
    override fun execute(parameters: Pair<String, String>): Flow<Result<Boolean>> {
        return repository.checkIfUserIsAttending(parameters.first,parameters.second)
    }

}