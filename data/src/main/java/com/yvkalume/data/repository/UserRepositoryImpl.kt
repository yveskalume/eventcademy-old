package com.yvkalume.data.repository

import com.yvkalume.domain.repository.UserRepository
import com.yvkalume.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override fun signIn(): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }
}