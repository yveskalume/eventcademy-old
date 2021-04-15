package com.yvkalume.data.repository

import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.repository.EventRepository
import com.yvkalume.util.Result
import kotlinx.coroutines.flow.Flow

class EventRepositoryImpl() : EventRepository {
    override fun getAll(): Flow<Result<List<Event>>> {
        TODO("Not yet implemented")
    }

    override fun getOneByUid(uid: String): Flow<Result<Event>> {
        TODO("Not yet implemented")
    }
}