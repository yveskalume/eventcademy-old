package com.yvkalume.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.data.seeder.EventsSeeder
import com.yvkalume.data.util.FireBasePath
import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.entity.User
import com.yvkalume.domain.repository.EventRepository
import com.yvkalume.util.Result
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
                if (error != null || value == null) {
                    trySend(Result.Error(Exception(error?.message.toString())))
                } else {
                    trySend(Result.Success(value.toObjects(Event::class.java)))
                }
            }
        awaitClose()
    }

    override fun getPastEvents(): Flow<Result<List<Event>>> {
        TODO("Not yet implemented")
    }

    override fun getOneByUid(uid: String): Flow<Result<Event>> {
        TODO("Not yet implemented")
    }

    override fun getOnline(): Flow<Result<List<Event>>> {
        TODO("Not yet implemented")
    }

    override fun getOffline(): Flow<Result<List<Event>>> {
        TODO("Not yet implemented")
    }

    override fun getNext(): Flow<Result<Event?>> {
        TODO("Not yet implemented")
    }

    override fun getAttendees(eventUid: String): Flow<Result<List<User>>> {
        TODO("Not yet implemented")
    }


}