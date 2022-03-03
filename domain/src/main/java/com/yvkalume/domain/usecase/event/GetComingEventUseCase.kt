package com.yvkalume.domain.usecase.event

import android.util.Log
import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.repository.EventRepository
import com.yvkalume.domain.util.FlowUseCase
import com.yvkalume.util.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetComingEventUseCase @Inject constructor(
    private val repository: EventRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<Event>>(dispatcher) {
    override fun execute(params: Unit): Flow<List<Event>> {
        Log.e(this::class.simpleName,"execute called")
        return repository.getComingEvents()
    }
}