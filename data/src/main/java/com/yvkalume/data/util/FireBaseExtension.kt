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

package com.yvkalume.data.util

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import com.yvkalume.util.Result
import kotlinx.coroutines.channels.awaitClose

@ExperimentalCoroutinesApi
inline fun <reified T> DocumentReference.collectAsFlow(): Flow<Result<T>> {
    return callbackFlow {
        addSnapshotListener { value, error ->
            if (error != null || value == null) {
                if (!isClosedForSend) {
                    trySend(Result.Error(Exception(error)))
                }
                close(error)
                return@addSnapshotListener
            }

            if (!isClosedForSend) {
                value.toObject<T>()?.let { data ->
                    trySend(Result.Success(data))
                }
            }
        }
        awaitClose()
    }
}

@ExperimentalCoroutinesApi
inline fun <reified T> DocumentReference.collectAsFlowNullable(): Flow<Result<T?>> {
    return callbackFlow {
        addSnapshotListener { value, error ->
            if (error != null || value == null) {
                if (!isClosedForSend) {
                    trySend(Result.Error(Exception(error)))
                }
                close(error)
                return@addSnapshotListener
            }

            if (!isClosedForSend) {
                trySend(Result.Success(value.toObject<T>()))
            }
        }
        awaitClose()
    }
}

@ExperimentalCoroutinesApi
inline fun <reified T : Any> Query.collectAsFlow(
    crossinline action: ((List<T>) -> List<T>
    ) = { data -> data }
): Flow<Result<List<T>>> {
    return callbackFlow {
        addSnapshotListener { value, error ->
            if (error != null || value == null) {
                if (!isClosedForSend) {
                    trySend(Result.Error(Exception(error)))
                }
                close(error)
                return@addSnapshotListener
            }

            if (!isClosedForSend) {
                trySend(Result.Success(action(value.toObjects())))
            }
        }
        awaitClose()
    }
}