package com.yvkalume.domain.repository

import com.yvkalume.domain.entity.User
import com.yvkalume.util.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun signInWithGoogle(idToken: String) : Result<Unit>
    suspend fun signInWithEmailAndPassword(idToken: String) : Result<Unit>
    fun add(user: User) : Flow<Result<Pair<Boolean,String>>>
    fun getUserByUid(uid: String) : Flow<Result<User>>
    suspend fun setHasGoingToAnEvent(user: User,eventUid: String)
}