package com.yvkalume.eventcademy.app

import android.app.Application
import android.content.Context
import android.content.Intent
import com.airbnb.mvrx.Mavericks
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }

    companion object {
        fun restart(context: Context) {
            context.packageManager.getLaunchIntentForPackage(context.packageName)?.apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addCategory(Intent.CATEGORY_HOME)
                context.startActivity(this)
            }
        }
    }
}