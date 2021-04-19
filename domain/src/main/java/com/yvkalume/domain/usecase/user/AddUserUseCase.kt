package com.yvkalume.domain.usecase.user

import com.yvkalume.domain.entity.User
import com.yvkalume.domain.repository.UserRepository
import com.yvkalume.domain.util.FlowUseCase
import com.yvkalume.util.Result
import com.yvkalume.util.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class AddUserUseCase(private val repository: UserRepository, @IoDispatcher dispatcher: CoroutineDispatcher) : FlowUseCase<User, Boolean>(dispatcher) {
    override fun execute(parameters: User): Flow<Result<Boolean>> {
        return repository.signIn()
    }
}