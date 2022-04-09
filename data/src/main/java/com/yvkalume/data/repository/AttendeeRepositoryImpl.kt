/*
 * Copyright (c) 2022 EventCademy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yvkalume.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.data.util.FireBasePath
import com.yvkalume.data.util.collectAsFlow
import com.yvkalume.domain.entity.Attendee
import com.yvkalume.domain.entity.getGeneratedUid
import com.yvkalume.domain.repository.AttendeeRepository
import com.yvkalume.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class AttendeeRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebasePath: FireBasePath
) : AttendeeRepository {

    override fun attendeeToAnEvent(attendee: Attendee) {
        firestore.document(firebasePath.getAttendeeDocument(attendee.getGeneratedUid())
        ).set(attendee.copy(uid = attendee.getGeneratedUid()))
    }

    override fun checkIfIsAttending(docUid: String) = flow {
        val doc = firestore.document(firebasePath.getAttendeeDocument(docUid)).get().await()
        emit(Result.Success(doc.exists()))
    }


    override fun getAttendeesByEventUid(eventUid: String): Flow<Result<List<Attendee>>> {
        val query = firestore.collection(firebasePath.attendees)
            .whereEqualTo(Attendee::eventUid.name,eventUid)
            .limit(5)
        return query.collectAsFlow()
    }
}