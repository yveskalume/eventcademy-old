package com.yvkalume.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.yvkalume.data.util.FireBasePath
import com.yvkalume.data.util.getOnlyFuture
import com.yvkalume.domain.entity.Club
import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.repository.ClubRepository
import com.yvkalume.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ClubRepositoryImpl @Inject constructor (private val firestore: FirebaseFirestore) : ClubRepository {
    @ExperimentalCoroutinesApi
    override fun getAllClubs() = callbackFlow {
        firestore.collection(FireBasePath.clubs)
                .addSnapshotListener { value, error ->
                    if (error != null && value == null) {
                        if (!isClosedForSend) {
                            offer(Result.Error(Exception("Une Erreur s'est produite")))
                        }
                        return@addSnapshotListener
                    }

                    value?.toObjects(Club::class.java)?.also {
                        if(!isClosedForSend)
                            offer(Result.Success(it))
                    }
                }
        awaitClose()
    }

    @ExperimentalCoroutinesApi
    override fun getOneByUid(uid: String) = callbackFlow {
        firestore.document("${FireBasePath.clubs}/$uid")
                .addSnapshotListener { value, error ->
                    if (error != null && value == null) {
                        if (!isClosedForSend) {
                            offer(Result.Error(Exception("Une Erreur s'est produite")))
                        }
                        return@addSnapshotListener
                    }

                    value?.toObject(Club::class.java)?.also {
                        if (!isClosedForSend)
                            offer(Result.Success(it))
                    }
                }
        awaitClose()
    }

    @ExperimentalCoroutinesApi
    override fun getClubEvents(uid: String) = callbackFlow {
        firestore.collection(FireBasePath.events)
                .whereEqualTo(Event::clubUid.name,uid)
                .addSnapshotListener { value, error ->
                    if (error != null && value == null) {
                        if (!isClosedForSend) {
                            offer(Result.Error(Exception("Une Erreur s'est produite")))
                        }
                        return@addSnapshotListener
                    }

                    value?.toObjects(Event::class.java)?.also {
                        if (!isClosedForSend)
                            offer(Result.Success(it.getOnlyFuture()))
                    }
                }
        awaitClose()
    }
}