package com.luisfagundes.result

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

suspend fun <T> safeAsyncRequest(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    call: suspend CoroutineScope.() -> T
): Result<T> {
    return try {
        val data = withContext(dispatcher) { call() }
        Result.Success(data)
    } catch (exception: Exception) {
        Timber.tag("safeAsyncRequest").e(exception.stackTraceToString())
        Result.Error(exception)
    }
}
