package com.yvkalume.eventcademy.ui.activity

import androidx.lifecycle.ViewModel
import com.yvkalume.domain.usecase.user.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor (private val addUserUseCase: AddUserUseCase): ViewModel() {
}