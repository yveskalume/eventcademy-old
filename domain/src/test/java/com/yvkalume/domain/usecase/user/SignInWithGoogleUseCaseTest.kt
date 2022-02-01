package com.yvkalume.domain.usecase.user

import com.yvkalume.domain.fakes.user.failureUserRepository
import com.yvkalume.domain.fakes.user.successfulUserRepository
import com.yvkalume.test.MainCoroutineRule
import com.yvkalume.test.runBlockingTest
import com.yvkalume.util.Result
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

internal class SignInWithGoogleUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Test
    @DisplayName("Given there is no error When Sign In with Google, Then get Result.Success")
    fun signInWithGoogleSuccess() = coroutineRule.runBlockingTest {
        val useCase = SignInWithGoogleUseCase(successfulUserRepository, coroutineRule.testDispatcher)
        val result = useCase("")
        assertTrue(result is Result.Success)

        val data = (result as Result.Success).data
        assertEquals(true,data)
    }

    @Test
    @DisplayName("Given there is an error When Sign In with Google, Then get Result.Error")
    fun signInWithGoogleFail() = coroutineRule.runBlockingTest {
        val useCase = SignInWithGoogleUseCase(failureUserRepository, coroutineRule.testDispatcher)
        val result = useCase("")

        assertTrue(result is Result.Error)

        val exception = (result as Result.Error).exception
        assertEquals(exception.message,Exception("Error").message)
    }
}