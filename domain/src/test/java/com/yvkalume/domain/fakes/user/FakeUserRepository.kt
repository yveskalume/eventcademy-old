package com.yvkalume.domain.fakes.user

import com.yvkalume.domain.dto.CustomFirebaseUser
import com.yvkalume.domain.entity.User
import com.yvkalume.domain.repository.UserRepository
import java.util.*

val successfulUserRepository = object : UserRepository {
    override suspend fun signInWithGoogle(idToken: String): CustomFirebaseUser {
        return CustomFirebaseUser(
            uid = "uid", email = "yves@gmail.com", name = "Yves Kalume", profileUrl = "https://profile/image.png", isValid = true,
            Date(System.currentTimeMillis())
        )
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean {
        return true
    }

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        name: String,
        password: String
    ): Boolean {
        return true
    }

    override suspend fun saveUserInDatabase(user: User) {

    }


}

val failureUserRepository = object : UserRepository {
    override suspend fun signInWithGoogle(idToken: String): CustomFirebaseUser {
        throw Exception("Error")
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean {
        throw Exception("Error")
    }

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        name: String,
        password: String
    ): Boolean {
        throw Exception("Error")
    }

    override suspend fun saveUserInDatabase(user: User) {
        throw Exception("Error")
    }

}