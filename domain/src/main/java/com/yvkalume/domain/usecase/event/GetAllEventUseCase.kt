package com.yvkalume.domain.usecase.event

import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.repository.EventRepository
import com.yvkalume.domain.util.FlowUseCase
import com.yvkalume.util.Result
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetAllEventUseCase constructor (dispatcher: MainCoroutineDispatcher, private val repository: EventRepository) : FlowUseCase<Unit, List<Event>>(dispatcher) {
    override fun execute(parameters: Unit): Flow<Result<List<Event>>> {
        return repository.getAll()
    }
}