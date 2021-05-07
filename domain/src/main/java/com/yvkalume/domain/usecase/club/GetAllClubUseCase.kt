package com.yvkalume.domain.usecase.club

import com.yvkalume.domain.entity.Club
import com.yvkalume.domain.repository.ClubRepository
import com.yvkalume.domain.util.FlowUseCase
import com.yvkalume.util.Result
import com.yvkalume.util.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetAllClubUseCase(private val repository: ClubRepository, @IoDispatcher dispatcher: CoroutineDispatcher) : FlowUseCase<Unit, List<Club>>(dispatcher) {
    override fun execute(parameters: Unit): Flow<Result<List<Club>>> {
        return repository.getAllClubs()
    }
}