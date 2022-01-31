package com.yvkalume.domain.usecase.user

import com.yvkalume.domain.repository.UserRepository
import com.yvkalume.domain.usecase.user.SignUpWithEmailAndPasswordUseCase.*
import com.yvkalume.domain.util.CoroutineUseCase
import com.yvkalume.util.Result
import com.yvkalume.util.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher

class SignUpWithEmailAndPasswordUseCase(
    private val repository: UserRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CoroutineUseCase<SignUpParams, Boolean>(dispatcher) {
    override suspend fun execute(parameters: SignUpParams): Result<Boolean> {
        TODO("Not yet implemented")
    }

    data class SignUpParams(val email: String, val name: String, val password: String)
}
