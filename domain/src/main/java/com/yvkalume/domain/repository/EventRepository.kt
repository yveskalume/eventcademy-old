package com.yvkalume.domain.repository

import com.yvkalume.domain.entity.Event
import com.yvkalume.util.Result
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getAll() : Flow<Result<List<Event>>>
    fun getOneByUid(uid: String) : Flow<Result<Event>>
    fun getOnline() : Flow<Result<List<Event>>>
}