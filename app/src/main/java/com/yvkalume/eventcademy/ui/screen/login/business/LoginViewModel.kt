package com.yvkalume.eventcademy.ui.screen.login.business

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.yvkalume.eventcademy.app.di.mavericks.AssistedViewModelFactory
import com.yvkalume.eventcademy.app.di.mavericks.hiltMavericksViewModelFactory
import kotlinx.coroutines.launch

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class LoginViewModel @AssistedInject constructor(
    @Assisted state: LoginViewState,
) : MavericksViewModel<LoginViewState>(state) {


    fun signInWithEmailAndPassword(email: String,password: String) = viewModelScope.launch {

    }

    fun signInWithGoogle(idToken: String) = viewModelScope.launch {

    }


    @AssistedFactory
    interface Factory : AssistedViewModelFactory<LoginViewModel, LoginViewState> {
        override fun create(state: LoginViewState): LoginViewModel
    }

    companion object : MavericksViewModelFactory<LoginViewModel, LoginViewState> by hiltMavericksViewModelFactory()
}
