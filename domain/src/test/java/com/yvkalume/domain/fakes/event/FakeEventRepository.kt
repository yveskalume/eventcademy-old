package com.yvkalume.domain.fakes.event

import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.entity.User
import com.yvkalume.domain.repository.EventRepository
import com.yvkalume.domain.ressources.EventTestData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val successfulEventRepository = object : EventRepository {
    override fun getComingEvents(): Flow<List<Event>> {
        return flow { emit(EventTestData.all) }
    }

    override fun getPastEvents(): Flow<List<Event>> {
        return flow {
            val events = EventTestData.all
            emit(events)
        }
    }

    override fun getOneByUid(uid: String): Flow<Event> {
        return flow {
            val events = EventTestData.all.filter { it.uid == uid }[0]
            emit(events)
        }
    }

    override fun getOnline(): Flow<List<Event>> {
        return flow {
            val events = EventTestData.all.filter { !it.offline }
            emit(events)
        }
    }

    override fun getOffline(): Flow<List<Event>> {
        return flow {
            val events = EventTestData.all.filter { !it.offline }
            emit(events)
        }
    }

    override fun getNext(): Flow<Event?> {
        return flow {
            val event = EventTestData.all.filter { !it.offline }[0]
            emit(event)
        }
    }

    override fun getAttendees(eventUid: String): Flow<List<User>> {
        TODO()
    }

}

val failureEventRepository = object : EventRepository {

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