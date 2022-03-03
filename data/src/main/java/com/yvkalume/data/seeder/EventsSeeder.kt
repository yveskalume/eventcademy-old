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

package com.yvkalume.data.seeder

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.data.util.FireBasePath
import com.yvkalume.data.util.FirebaseSeeder
import com.yvkalume.domain.entity.Event
import com.yvkalume.util.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class EventsSeeder @Inject constructor(
    val firestore: FirebaseFirestore,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : FirebaseSeeder(dispatcher) {


    override val quantity: Int = 4

    val event = Event(
        uid = "",
        title = faker.funnyName().name(),
        description = faker.lorem().fixedString(200),
        attendeesUids = arrayListOf(),
        location = faker.address().fullAddress(),
        link = faker.avatar().image(),
        imageUrl = faker.avatar().image(),
        offline = faker.bool().bool(),
        startDate = faker.date().future(1,TimeUnit.DAYS),
        endDate = faker.date().future(1,TimeUnit.DAYS),
        longitude = 0,
        latitude = 0,
        createdAt = null,
        creatorUid = ""
    )

    override suspend fun seed() {
        withContext(Dispatchers.IO) {
            Log.e("EventsSeeder","seed called")
            firestore.collection(FireBasePath.events)
                .add(this@EventsSeeder.event).await()
        }
    }
}