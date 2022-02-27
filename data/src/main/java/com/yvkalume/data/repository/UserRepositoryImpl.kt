package com.yvkalume.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.data.util.FireBasePath
import com.yvkalume.domain.dto.CustomFirebaseUser
import com.yvkalume.domain.entity.User
import com.yvkalume.domain.repository.UserRepository
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore) :
    UserRepository {

    override suspend fun signInWithGoogle(idToken: String): CustomFirebaseUser {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        try {
            val user = firebaseAuth.signInWithCredential(credential).await().user!!
            return CustomFirebaseUser(
                uid = user.uid,
                email = user.email.toString(),
                name = user.displayName.toString(),
                profileUrl = user.photoUrl.toString(),
                isValid = true,
                createdAt = Date(System.currentTimeMillis())
            )
        } catch (t: Throwable) {
            throw t
        }
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        name: String,
        password: String
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun saveUserInDatabase(user: User) {
        val docRef = firestore.document(FireBasePath.getUserDocument(user.uid))
        docRef.set(user).await()
    }

}