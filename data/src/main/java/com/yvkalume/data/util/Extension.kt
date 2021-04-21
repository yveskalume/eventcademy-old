package com.yvkalume.data.util

import com.yvkalume.domain.entity.Event

fun List<Event>.getOnlyFuture() : List<Event> {
    sortedBy { it.date }
    return filter { event ->
        val now = System.currentTimeMillis()
        return@filter  now <= event.date!!.time + 3600000
    }
}