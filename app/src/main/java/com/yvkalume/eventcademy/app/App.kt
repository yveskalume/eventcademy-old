package com.yvkalume.eventcademy.app

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.yvkalume.eventcademy.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        setUpFcm()
    }

    private fun setUpFcm() {
        FirebaseApp.initializeApp(this)
        FirebaseAuth.getInstance().currentUser?.uid?.let {
            FirebaseMessaging.getInstance().subscribeToTopic(it)
        }
    }

}