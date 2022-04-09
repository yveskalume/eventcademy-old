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

package com.yvkalume.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

val Boolean?.orFalse : Boolean
        get() = this ?: false

@SuppressLint("SimpleDateFormat")
fun Date.toHumanDate(): String {
    return try {
        SimpleDateFormat("EE dd MMMM yyyy à HH:mm", Locale.FRANCE).format(this)
    } catch (e: Exception) {
        e.toString()
    }
}

val Date.humanMonth: String
    get() = SimpleDateFormat("MMM", Locale.FRANCE).format(this)
val Date.humanMonthDay: String
    get() = SimpleDateFormat("dd", Locale.FRANCE).format(this)


@SuppressLint("SimpleDateFormat")
class DateUtil {
    companion object {
        fun fromTo(startDate: Date?, endDate: Date?): String {
            val humanDate = SimpleDateFormat("EE dd MMMM", Locale.FRANCE).format(startDate)
            return "$humanDate - De ${startDate?.hours}:${startDate?.minutes} à ${endDate?.hours}:${endDate?.minutes}"
        }
    }
}