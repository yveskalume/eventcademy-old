package com.yvkalume.domain.fakes.event

import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.entity.User
import com.yvkalume.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

class FakeEventRepositorySuccess : EventRepository {
    override fun getComingEvents(): Flow<List<Event>> {
        TODO("Not yet implemented")
    }

    override fun getPastEvents(): Flow<List<Event>> {
        TODO("Not yet implemented")
    }

    override fun getOneByUid(uid: String): Flow<Event> {
        TODO("Not yet implemented")
    }

    override fun getOnline(): Flow<List<Event>> {
        TODO("Not yet implemented")
    }

    override fun getOffline(): Flow<List<Event>> {
        TODO("Not yet implemented")
    }

    override fun getNext(): Flow<Event?> {
        TODO("Not yet implemented")
    }

    override fun getAttendees(eventUid: String): Flow<List<User>> {
        TODO("Not yet implemented")
    }
}