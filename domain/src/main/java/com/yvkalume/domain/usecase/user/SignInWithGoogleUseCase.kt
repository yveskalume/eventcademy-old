package com.yvkalume.domain.usecase.user

import com.yvkalume.domain.repository.UserRepository
import com.yvkalume.domain.util.CoroutineUseCase
import com.yvkalume.util.Result
import com.yvkalume.util.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(
    private val repository: UserRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<String, Boolean>(dispatcher) {
    override suspend fun execute(params: String): Boolean {
        return repository.signInWithGoogle(idToken = params)
    }
}