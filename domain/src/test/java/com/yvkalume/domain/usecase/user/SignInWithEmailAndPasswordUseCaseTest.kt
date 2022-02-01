package com.yvkalume.domain.usecase.user

import com.yvkalume.domain.fakes.user.failureUserRepository
import com.yvkalume.domain.fakes.user.successfulUserRepository
import com.yvkalume.domain.usecase.user.SignInWithEmailAndPasswordUseCase.*
import com.yvkalume.test.MainCoroutineRule
import com.yvkalume.test.runBlockingTest
import com.yvkalume.util.Result
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

internal class SignInWithEmailAndPasswordUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Test
    @DisplayName("Given there is no error When Sign In with Email, Then get Result.Success")
    fun signInWithEmailSuccess() = coroutineRule.runBlockingTest {
        val useCase = SignInWithEmailAndPasswordUseCase(successfulUserRepository, coroutineRule.testDispatcher)
        val result = useCase(SignInWithEmailParams("",""))
        assertTrue(result is Result.Success)
    }

    @Test
    @DisplayName("Given there is an error When Sign In with Email and Password, Then get Result.Error")
    fun signInWithEmailFail() = coroutineRule.runBlockingTest {
        val useCase = SignInWithEmailAndPasswordUseCase(failureUserRepository, coroutineRule.testDispatcher)
        val result = useCase(SignInWithEmailParams("",""))
        assertEquals(result, Result.Error(Exception("Error")))
    }
}