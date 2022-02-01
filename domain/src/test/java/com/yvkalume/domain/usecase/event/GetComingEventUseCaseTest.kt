package com.yvkalume.domain.usecase.event

import com.yvkalume.domain.fakes.event.failureEventRepository
import com.yvkalume.domain.fakes.event.successfulEventRepository
import com.yvkalume.domain.ressources.EventTestData
import com.yvkalume.test.MainCoroutineRule
import com.yvkalume.test.runBlockingTest
import com.yvkalume.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName

@Disabled
internal class GetComingEventUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()



    @Test
    @DisplayName("Given have events When Get Coming Event, Then Get Result.Succes")
    fun getComingEventSuccess() = coroutineRule.runBlockingTest {
        val useCase = GetComingEventUseCase(successfulEventRepository,coroutineRule.testDispatcher)
        val result = useCase(Unit)
        assertEquals(result,Result.Success(EventTestData.all))
    }

    @Test
    @DisplayName("Given Error on server When Get Coming Event, Then Get Result.Error")
    fun getComingEventFailure() = coroutineRule.runBlockingTest {
        val useCase = GetComingEventUseCase(failureEventRepository,coroutineRule.testDispatcher)
        val result = useCase(Unit)
        assertEquals(result,Result.Error(Exception("Error")))
    }

}