package com.yvkalume.domain.usecase.event

import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.repository.EventRepository
import com.yvkalume.domain.util.FlowUseCase
import com.yvkalume.util.Result
import com.yvkalume.util.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOneEventByUidUseCase @Inject constructor(private val repository: EventRepository, @IoDispatcher private val dispatcher: CoroutineDispatcher ) : FlowUseCase<String, Event>(dispatcher) {
    override fun execute(parameters: String): Flow<Result<Event>> {
       return repository.getOneByUid(parameters)
    }

}