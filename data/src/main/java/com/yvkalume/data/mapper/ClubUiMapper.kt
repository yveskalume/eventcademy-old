package com.yvkalume.data.mapper

import com.yvkalume.data.model.ClubUiModel
import com.yvkalume.data.util.Mapper
import com.yvkalume.domain.entity.Club

class ClubUiMapper : Mapper<Club, ClubUiModel> {
    override fun map(t: Club): ClubUiModel {
        return ClubUiModel(
                uid = t.uid,
                title = t.title,
                description = t.description,
                imageUrl = t.imageUrl
        )
    }
}