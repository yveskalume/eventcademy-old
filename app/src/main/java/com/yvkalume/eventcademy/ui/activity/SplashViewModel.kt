package com.yvkalume.eventcademy.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yvkalume.domain.entity.User
import com.yvkalume.domain.usecase.user.AddUserUseCase
import com.yvkalume.domain.usecase.user.SignInWithGoogleUseCase
import com.yvkalume.eventcademy.util.SingleLiveEvent
import com.yvkalume.util.data
import com.yvkalume.util.succeeded
import com.yvkalume.util.successOr
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor (private val signInWithGoogleUseCase: SignInWithGoogleUseCase, private val addUserUseCase: AddUserUseCase): ViewModel() {

    private val _userAdded = MutableLiveData<SingleLiveEvent<Pair<Boolean,String>>>()
    val userAdded: LiveData<SingleLiveEvent<Pair<Boolean,String>>>
        get() = _userAdded

    fun signIn(idToken: String) = viewModelScope.launch {
        signInWithGoogleUseCase(idToken).collect {
            if (it.succeeded) {
                addUser(it.data!!)
            } else {
                _userAdded.value = SingleLiveEvent(Pair(false,"Une erreur s'est produite"))
            }
        }
    }

    private fun addUser(user: User) = viewModelScope.launch {
        addUserUseCase(user).collect {
            _userAdded.value = SingleLiveEvent(it.data!!)
        }
    }
}