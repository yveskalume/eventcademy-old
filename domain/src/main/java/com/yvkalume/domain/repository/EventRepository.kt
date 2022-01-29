package com.yvkalume.domain.repository

import com.yvkalume.domain.entity.Event
import com.yvkalume.domain.entity.User
import com.yvkalume.util.Result
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getComingEvents() : Flow<List<Event>>
    fun getPastEvents() : Flow<List<Event>>
    fun getOneByUid(uid: String) : Flow<Event>
    fun getOnline() : Flow<List<Event>>
    fun getOffline() : Flow<List<Event>>
    fun getNext() : Flow<Event?>
    fun getAttendees(eventUid: String) : Flow<List<User>>
}