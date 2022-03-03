package com.yvkalume.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.data.seeder.EventsSeeder
import com.yvkalume.data.util.FireBasePath
import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.entity.User
import com.yvkalume.domain.repository.EventRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class EventRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : EventRepository {

    override fun getComingEvents() = callbackFlow {
        firestore.collection(FireBasePath.events)
            .addSnapshotListener { value, error ->
                trySend(value!!.toObjects(Event::class.java))
            }
        awaitClose()
    }.catch {
        throw it
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