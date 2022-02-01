package com.yvkalume.domain.fakes.user

import com.yvkalume.domain.repository.UserRepository

val successfulUserRepository = object : UserRepository {
    override suspend fun signInWithGoogle(idToken: String) : Boolean {
        return true
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean {
        return true
    }


}

val failureUserRepository = object : UserRepository {
    override suspend fun signInWithGoogle(idToken: String) : Boolean  {
        throw Exception("Error")
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean {
        throw Exception("Error")
    }

}