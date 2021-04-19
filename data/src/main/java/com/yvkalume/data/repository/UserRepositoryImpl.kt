package com.yvkalume.data.repository

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.data.util.FireBasePath
import com.yvkalume.domain.entity.User
import com.yvkalume.domain.repository.UserRepository
import com.yvkalume.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore,private val auth: FirebaseAuth) : UserRepository {
    @ExperimentalCoroutinesApi
    override fun signIn(idToken: String) = callbackFlow {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("UserRepository", "signInWithCredential:success")
                    val authUser = auth.currentUser
                    if (authUser != null) {
                        val user = User(auth.uid!!,authUser.displayName!!,authUser.email!!, Timestamp.now().toDate())
                        if(!isClosedForSend) {
                            offer(Result.Success(user))
                        }
                    }
                } else {
                    if(!isClosedForSend) {
                        offer(Result.Error(Exception("Erreur")))
                    }
                    Log.w(this.toString(), "signInWithCredential:failure", task.exception)
                }
            }
        awaitClose()
    }

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
        awaitClose()
    }
}