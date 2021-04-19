package com.yvkalume.domain.repository

import com.yvkalume.domain.entity.User
import com.yvkalume.util.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun add(user: User) : Flow<Result<Boolean>>
}