package com.yvkalume.domain.repository

import com.yvkalume.util.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun add() : Flow<Result<Boolean>>
}