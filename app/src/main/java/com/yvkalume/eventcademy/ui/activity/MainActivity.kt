package com.yvkalume.eventcademy.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.epoxy.Carousel
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE
import com.google.android.play.core.install.model.UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
import com.google.android.play.core.install.model.UpdateAvailability.UPDATE_AVAILABLE
import com.yvkalume.eventcademy.R
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val appUpdateManager by lazy {
        AppUpdateManagerFactory.create(baseContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Carousel.setDefaultGlobalSnapHelperFactory(null)
        checkUpdate()
    }

    override fun onResume() {
        super.onResume()
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.updateAvailability() == DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                appUpdateManager.startUpdateFlowForResult(it, IMMEDIATE, this, UPDATE_REQUEST_CODE)
            }
        }
    }

    private fun checkUpdate() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.updateAvailability() == UPDATE_AVAILABLE && it.isUpdateTypeAllowed(IMMEDIATE)) {
                appUpdateManager.startUpdateFlowForResult(it, IMMEDIATE, this, UPDATE_REQUEST_CODE)
            }
        }
    }

    companion object {
        private const val UPDATE_REQUEST_CODE = 1
    }
}