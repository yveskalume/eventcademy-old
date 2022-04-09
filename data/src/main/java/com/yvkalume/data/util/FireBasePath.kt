package com.yvkalume.data.util

object FireBasePath {
    const val events = "test-events"
    const val users = "users"
    const val clubs = "clubs"
    const val attendees = "attendees"

    fun getAttendeeDocument(docUid: String) : String {
        return "${this.attendees}/$docUid"
    }

    fun getEventDocument(docUid: String) : String {
        return "${this.events}/$docUid"
    }

    fun getUserDocument(docUid: String) : String {
        return "${this.users}/$docUid"
    }
}