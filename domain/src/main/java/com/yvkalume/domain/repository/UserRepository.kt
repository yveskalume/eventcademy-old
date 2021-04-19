package com.yvkalume.domain.repository

import com.yvkalume.util.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun signIn() : Flow<Result<Boolean>>
}