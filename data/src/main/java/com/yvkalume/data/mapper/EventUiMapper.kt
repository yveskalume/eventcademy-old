package com.yvkalume.data.mapper

import com.yvkalume.data.model.EventUiModel
import com.yvkalume.data.util.Mapper
import com.yvkalume.domain.entity.Event
import java.time.format.DateTimeFormatter

class EventUiMapper() : Mapper<Event,EventUiModel> {

    override fun map(t: Event): EventUiModel {
        return EventUiModel(
            uid = t.uid,
            title = t.title,
            description = t.description,
            imageUrl = t.imageUrl,
            date = t.date,
            createdAt = t.createdAt
        )
    }
}