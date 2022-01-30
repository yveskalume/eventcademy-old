package com.yvkalume.domain.fakes.event

import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.entity.User
import com.yvkalume.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

class FakeEventRepositoryFailure : EventRepository {

    override fun getComingEvents(): Flow<List<Event>> {
        throw Exception("Error")
    }

    override fun getPastEvents(): Flow<List<Event>> {
        throw Exception("Error")
    }

    override fun getOneByUid(uid: String): Flow<Event> {
        throw Exception("Error")
    }

    override fun getOnline(): Flow<List<Event>> {
        throw Exception("Error")
    }

    override fun getOffline(): Flow<List<Event>> {
        throw Exception("Error")
    }

    override fun getNext(): Flow<Event?> {
        throw Exception("Error")
    }

    override fun getAttendees(eventUid: String): Flow<List<User>> {
        throw Exception("Error")
    }

}