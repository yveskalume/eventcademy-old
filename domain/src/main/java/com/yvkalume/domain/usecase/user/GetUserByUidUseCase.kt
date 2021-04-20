package com.yvkalume.domain.usecase.user

import com.yvkalume.domain.entity.User
import com.yvkalume.domain.repository.UserRepository
import com.yvkalume.domain.util.FlowUseCase
import com.yvkalume.util.Result
import com.yvkalume.util.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserByUidUseCase @Inject constructor(private val repository: UserRepository, @IoDispatcher dispatcher: CoroutineDispatcher) : FlowUseCase<String,User>(dispatcher) {
    override fun execute(parameters: String): Flow<Result<User>> {
        return repository.getUserByUid(parameters)
    }
}