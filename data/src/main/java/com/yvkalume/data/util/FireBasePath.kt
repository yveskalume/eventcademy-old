package com.yvkalume.data.util

object FireBasePath {
    const val events = "test-events"
    const val users = "users"
    const val clubs = "clubs"
    fun getAttendeesCollection(eventUid: String) : String {
        return "${this.events}/$eventUid/attendees"
    }

    fun getEventDocument(docUid: String) : String {
        return "${this.events}/$docUid"
    }

    fun getUserDocument(docUid: String) : String {
        return "${this.users}/$docUid"
    }
}