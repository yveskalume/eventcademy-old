package com.yvkalume.domain.repository

import com.yvkalume.domain.entity.Club
import com.yvkalume.domain.entity.Event
import kotlinx.coroutines.flow.Flow
import com.yvkalume.util.Result

interface ClubRepository {
    fun getAllClubs() : Flow<Result<List<Club>>>
    fun getOneByUid(uid: String) : Flow<Result<Club>>
    fun getClubEvents(uid: String) : Flow<Result<List<Event>>>
}