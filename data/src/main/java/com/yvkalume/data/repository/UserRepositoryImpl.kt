package com.yvkalume.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.data.util.FireBasePath
import com.yvkalume.domain.entity.User
import com.yvkalume.domain.repository.UserRepository
import com.yvkalume.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore) : UserRepository {
    @ExperimentalCoroutinesApi
    override fun add(user: User) = callbackFlow {
        firestore.collection(FireBasePath.users)
                .document(user.uid)
                .set(user)
                .addOnSuccessListener {
                    if (!isClosedForSend) {
                        offer(Result.Success(Pair(true,"Success")))
                    }
                }
                .addOnFailureListener {
                    if (!isClosedForSend) {
                        offer(Result.Success(Pair(false,it.localizedMessage!!)))
                    }
                }
    }
}