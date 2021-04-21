package com.yvkalume.data.util

object FireBasePath {
    const val events = "events"
    const val users = "users"
    fun getAttendeesCollection(eventUid: String) : String {
        return "${this.events}/$eventUid/attendees"
    }
}