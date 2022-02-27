package com.yvkalume.eventcademy.ui.screen.splash.business

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val pref: SharedPreferences,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _isAuth = MutableStateFlow(false)
    val isAuth: StateFlow<Boolean>
        get() = _isAuth

    init {
        getAuthState()
    }

    private fun getAuthState() = viewModelScope.launch {
        _isAuth.emit(pref.getBoolean("is-auth", false))
    }

}