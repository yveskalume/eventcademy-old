package com.yvkalume.domain.usecase.user

import com.yvkalume.domain.entity.User
import com.yvkalume.domain.repository.UserRepository
import com.yvkalume.domain.util.CoroutineUseCase
import com.yvkalume.domain.util.FlowUseCase
import com.yvkalume.util.Result
import com.yvkalume.util.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetHasGoingToAnEventUseCase @Inject constructor(private val repository: UserRepository, @IoDispatcher dispatcher: CoroutineDispatcher) : CoroutineUseCase<Pair<User,String>,Unit>(dispatcher) {
    override suspend fun execute(parameters: Pair<User, String>) {
        return repository.setHasGoingToAnEvent(parameters.first,parameters.second)
    }


}