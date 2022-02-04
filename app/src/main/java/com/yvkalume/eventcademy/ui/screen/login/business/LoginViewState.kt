package com.yvkalume.eventcademy.ui.screen.login.business

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class LoginViewState(val data: Async<Boolean> = Uninitialized) : MavericksState