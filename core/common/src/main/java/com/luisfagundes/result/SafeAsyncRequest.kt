package com.luisfagundes.result

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> safeAsyncRequest(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    call: suspend CoroutineScope.() -> T
): Result<T> {
    return try {
        val data = withContext(dispatcher) { call() }
        Result.Success(data)
    } catch (exception: Exception) {
        Log.e("safeAsyncRequest", exception.stackTraceToString())
        Result.Error(exception)
    }
}