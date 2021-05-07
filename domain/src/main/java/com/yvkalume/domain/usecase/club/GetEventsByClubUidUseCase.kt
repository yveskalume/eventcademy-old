package com.yvkalume.domain.usecase.club

import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.repository.ClubRepository
import com.yvkalume.domain.util.FlowUseCase
import com.yvkalume.util.Result
import com.yvkalume.util.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetEventsByClubUidUseCase (private val repository: ClubRepository, @IoDispatcher private val dispatcher: CoroutineDispatcher) : FlowUseCase<String, List<Event>>(dispatcher) {
    override fun execute(parameters: String): Flow<Result<List<Event>>> {
        return repository.getClubEvents(parameters)
    }
}