package com.yvkalume.domain.usecase.event

import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.repository.EventRepository
import com.yvkalume.domain.util.FlowUseCase
import com.yvkalume.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetOneEventByUidUseCase(dispatcher: CoroutineDispatcher, private val repository: EventRepository) : FlowUseCase<String, Event>(dispatcher) {
    override fun execute(parameters: String): Flow<Result<Event>> {
        return repository.getOneByUid(parameters)
    }
}