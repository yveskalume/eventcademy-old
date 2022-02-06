package com.yvkalume.eventcademy.ui.screen.splash.business

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val pref: SharedPreferences) : ViewModel() {

    private val _isAuth = MutableStateFlow(false)
    val isAuth: StateFlow<Boolean>
        get() = _isAuth

    init {
        getAuthState()
    }

    private fun getAuthState() {
        pref.getBoolean("is-auth",false)
    }

}