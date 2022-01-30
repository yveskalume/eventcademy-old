package com.yvkalume.domain.ressources

import com.yvkalume.domain.entity.Event
import java.time.ZonedDateTime
import java.util.*
import kotlin.collections.ArrayList

object EventTestData {

    val all = listOf<Event>(
        Event(
            uid = "1",
            title = "Event 1",
            description = "description 1",
            attendeesUids = arrayListOf(),
            location = "lubumbashi",
            link = "https://lorem.com",
            imageUrl = "https://lorem.com/img.png",
            offline = true,
            startDate = Date(),
            endDate = Date(),
            longitude = 0,
            latitude = 0,
            createdAt = Date(),
            creatorUid = "1"
        )

    )
}