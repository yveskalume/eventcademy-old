package com.yvkalume.data.util

object FireBasePath {
    const val events = "events"
    const val users = "users"
    const val clubs = "clubs"
    fun getAttendeesCollection(eventUid: String) : String {
        return "${this.events}/$eventUid/attendees"
    }
}