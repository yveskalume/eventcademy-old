package com.yvkalume.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.data.util.FireBasePath
import com.yvkalume.data.util.collectAsFlow
import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.entity.User
import com.yvkalume.domain.repository.EventRepository
import com.yvkalume.util.Result
import com.yvkalume.util.data
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class EventRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : EventRepository {
    override fun getComingEvents(): Flow<Result<List<Event>>> {
        val query = firestore.collection(FireBasePath.events)
            .whereGreaterThan(Event::endDate.name, Date(System.currentTimeMillis()))
        return query.collectAsFlow { it.sortedBy { event -> event.startDate } }
    }

    override fun getPastEvents(): Flow<Result<List<Event>>> {
        val query = firestore.collection(FireBasePath.events)
            .whereLessThan(Event::endDate.name, Date(System.currentTimeMillis()))
        return query.collectAsFlow { it.sortedByDescending { event -> event.endDate } }
    }

    override fun getOneByUid(uid: String): Flow<Result<Event>> {
        val query = firestore.document(FireBasePath.getEventDocument(uid))
        return query.collectAsFlow()
    }

    override fun getOnline(): Flow<Result<List<Event>>> {
        val query = firestore.collection(FireBasePath.events)
            .whereEqualTo(Event::offline.name, false)
        return query.collectAsFlow()
    }

    override fun getOffline(): Flow<Result<List<Event>>> {
        val query = firestore.collection(FireBasePath.events)
            .whereEqualTo(Event::offline.name, true)
        return query.collectAsFlow()
    }

    override fun getNext() = flow {
        this@EventRepositoryImpl.getComingEvents().collect {
            try {
                emit(Result.Success(it.data?.first()))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
    }

    override fun getAttendees(eventUid: String): Flow<Result<List<User>>> {
        val query = firestore.collection(FireBasePath.getAttendeesCollection(eventUid))
        return query.collectAsFlow()
    }


}