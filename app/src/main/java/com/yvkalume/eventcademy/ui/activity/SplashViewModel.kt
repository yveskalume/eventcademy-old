package com.yvkalume.eventcademy.ui.activity

import androidx.lifecycle.ViewModel
import com.yvkalume.domain.usecase.user.UserSignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor (private val userSignInUseCase: UserSignInUseCase): ViewModel() {
}