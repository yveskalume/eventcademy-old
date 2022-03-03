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

import android.content.Context
import android.util.Log
import com.github.javafaker.Faker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.util.Result
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Generate [quantity] fake data in firebase
 * @returns a [Result]
 */

abstract class FirebaseSeeder(private val dispatcher: CoroutineDispatcher) {

    abstract val quantity: Int

    protected val faker = Faker()

    abstract suspend fun seed()

    suspend operator fun invoke(): Result<String> {

        return try {
            for (i in 0..quantity) {
                withContext(dispatcher) {
                    seed()
                }
            }
            Result.Success("Success")
        } catch (e: Exception) {
            Log.e("FirebaseSeeder",e.message.toString())
            Result.Error(e)
        }
    }
}