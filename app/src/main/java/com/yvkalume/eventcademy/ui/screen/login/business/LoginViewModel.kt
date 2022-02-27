package com.yvkalume.eventcademy.ui.screen.login.business

import android.content.SharedPreferences
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.yvkalume.domain.dto.toUser
import com.yvkalume.domain.usecase.user.SaveUserInDatabaseUseCase
import com.yvkalume.domain.usecase.user.SignInWithGoogleUseCase
import com.yvkalume.eventcademy.app.di.mavericks.AssistedViewModelFactory
import com.yvkalume.eventcademy.app.di.mavericks.hiltMavericksViewModelFactory
import kotlinx.coroutines.launch
import com.yvkalume.util.Result
import com.yvkalume.util.data

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class LoginViewModel @AssistedInject constructor(
    @Assisted state: LoginViewState,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val saveUserInDatabaseUseCase: SaveUserInDatabaseUseCase,
    private val pref: SharedPreferences,
) : MavericksViewModel<LoginViewState>(state) {


    fun signInWithEmailAndPassword(email: String,password: String) = viewModelScope.launch {

    }

    fun signInWithGoogle(idToken: String) = viewModelScope.launch {
        suspend {
            val firebaseUser = signInWithGoogleUseCase(idToken).data!!
            saveUserInDatabaseUseCase(firebaseUser.toUser()).data!!

            val editor = pref.edit()
            editor.apply {
                putBoolean("is-auth", true)
            }.apply()

        }.execute {
            copy(isSuccess = it)
        }
    }


    @AssistedFactory
    interface Factory : AssistedViewModelFactory<LoginViewModel, LoginViewState> {
        override fun create(state: LoginViewState): LoginViewModel
    }

    companion object : MavericksViewModelFactory<LoginViewModel, LoginViewState> by hiltMavericksViewModelFactory()
}
