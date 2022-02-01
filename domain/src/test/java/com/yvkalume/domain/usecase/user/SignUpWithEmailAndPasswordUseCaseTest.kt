package com.yvkalume.domain.usecase.user

import com.yvkalume.domain.fakes.user.failureUserRepository
import com.yvkalume.domain.fakes.user.successfulUserRepository
import com.yvkalume.domain.usecase.user.SignInWithEmailAndPasswordUseCase.SignInWithEmailParams
import com.yvkalume.test.MainCoroutineRule
import com.yvkalume.test.runBlockingTest
import com.yvkalume.util.Result
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName

internal class SignUpWithEmailAndPasswordUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Test
    @DisplayName("Given there is no error, When user signup With Email and Password, Then get Result.Success")
    fun signInWithEmailSuccess() = coroutineRule.runBlockingTest {
        val useCase = SignInWithEmailAndPasswordUseCase(
            successfulUserRepository,
            coroutineRule.testDispatcher
        )
        val result = useCase(SignInWithEmailParams("", ""))
        assertTrue(result is Result.Success)

        val data = (result as Result.Success).data
        assertEquals(true, data)

    }

    @Test
    @DisplayName("Given there is an error, When user signup With Email and Password, Then get Result.Error")
    fun signInWithEmailFail() = coroutineRule.runBlockingTest {
        val useCase =
            SignInWithEmailAndPasswordUseCase(failureUserRepository, coroutineRule.testDispatcher)
        val result = useCase(SignInWithEmailParams("", ""))

        assertTrue(result is Result.Error)

        val exception = (result as Result.Error).exception
        assertEquals(exception.message, Exception("Error").message)
    }
}