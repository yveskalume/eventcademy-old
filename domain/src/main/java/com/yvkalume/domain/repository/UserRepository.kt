package com.yvkalume.domain.repository

import com.yvkalume.domain.entity.User
import com.yvkalume.util.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun signInWithGoogle(idToken: String) : Boolean
    suspend fun signInWithEmailAndPassword(email: String, password: String) : Boolean
    suspend fun signUpWithEmailAndPassword(email: String, name: String, password: String) : Boolean
}