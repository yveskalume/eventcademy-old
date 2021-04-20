package com.yvkalume.domain.usecase.event

import com.yvkalume.domain.entity.User
import com.yvkalume.domain.repository.EventRepository
import com.yvkalume.domain.util.FlowUseCase
import com.yvkalume.util.Result
import com.yvkalume.util.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAttendeesUseCase @Inject constructor (private val repository: EventRepository, @IoDispatcher private val dispatcher: CoroutineDispatcher) : FlowUseCase<String,List<User>>(dispatcher) {
    override fun execute(parameters: String): Flow<Result<List<User>>> {
        return repository.getAttendees(parameters)
    }
}