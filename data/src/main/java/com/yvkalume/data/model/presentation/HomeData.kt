package com.yvkalume.data.model.presentation

import com.yvkalume.data.model.EventUiModel

data class HomeData (
    val featuredEvent: EventUiModel?,
    val onlineEvents: List<EventUiModel>?,
    val offlineEvents: List<EventUiModel>?
)
