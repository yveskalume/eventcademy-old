package com.yvkalume.eventcademy.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.airbnb.epoxy.Carousel
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE
import com.google.android.play.core.install.model.UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
import com.google.android.play.core.install.model.UpdateAvailability.UPDATE_AVAILABLE
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.databinding.ActivityMainBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val appUpdateManager by lazy {
        AppUpdateManagerFactory.create(baseContext)
    }

    private val navController by lazy {
        Navigation.findNavController(this, R.id.fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Carousel.setDefaultGlobalSnapHelperFactory(null)
        checkUpdate()
        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation() {
        binding.bottomNavigation.setupWithNavController(navController)
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