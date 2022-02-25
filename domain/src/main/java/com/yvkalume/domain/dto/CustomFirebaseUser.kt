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

package com.yvkalume.domain.dto

import com.yvkalume.domain.entity.User
import java.util.*

data class CustomFirebaseUser(
    val uid: String,
    val email: String,
    val name: String,
    val profileUrl: String,
    val isValid : Boolean,
    val createdAt : Date
)

fun CustomFirebaseUser.toUser(): User {
    return User(
        uid = uid,
        name = name,
        email = email,
        profileUrl = profileUrl,
        isValid = isValid,
        createdAt = createdAt
    )
}