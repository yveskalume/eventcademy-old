package com.yvkalume.domain.util

import kotlinx.coroutines.CoroutineDispatcher
import com.yvkalume.util.Result
import kotlinx.coroutines.flow.*

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Result.Error] to the result) is the subclasses's responsibility.
 */
abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameters: P): Flow<Result<R>> = flow<Result<R>> {
        execute(parameters).map { Result.Success(it) }
    }.catch { e -> emit(Result.Error(Exception(e))) }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(params: P): Flow<R>
}