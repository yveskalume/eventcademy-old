package com.yvkalume.eventcademy.util

import android.content.Intent
import android.provider.CalendarContract
import androidx.core.content.ContextCompat.startActivity
import com.yvkalume.data.model.EventUiModel


fun EventUiModel.addToGoogleAgendaIntent(): Intent {
    val intent = Intent(Intent.ACTION_INSERT)
        .setData(CalendarContract.Events.CONTENT_URI)
        .putExtra(CalendarContract.Events.CALENDAR_ID,uid)
        .putExtra(CalendarContract.Events.TITLE, title)
        .putExtra(CalendarContract.Events.DESCRIPTION, description)
        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, date!!.time)
        .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
        .putExtra(CalendarContract.Events.EVENT_LOCATION, lieu)
        .putExtra(CalendarContract.Events.EVENT_TIMEZONE, "Africa/Lubumbashi")
        .putExtra(CalendarContract.Events.CAN_MODIFY_TIME_ZONE, true)

    return intent
}