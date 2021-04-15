package com.yvkalume.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.data.util.FireBasePath
import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.repository.EventRepository
import com.yvkalume.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore) : EventRepository {
    @ExperimentalCoroutinesApi
    override fun getAll() = callbackFlow {
        firestore.collection(FireBasePath.events)
            .addSnapshotListener { value, error ->
                if (error != null && value == null) {
                    if (!isClosedForSend) {
                        offer(Result.Error(Exception("Une Erreur s'est produite")))
                    }
                    return@addSnapshotListener
                }

                value?.toObjects(Event::class.java)?.also {
                    offer(Result.Success(it))
                }
            }
        awaitClose()
    }

    override fun getOneByUid(uid: String): Flow<Result<Event>> {
        TODO("Not yet implemented")
    }
}