package com.luisfagundes.result

import android.util.Log

@JvmName("safeApiCall")
suspend fun <T> safeApiCall(call: suspend () -> T): Result<T> {
    val data = call()
    return try {
        Result.Success(data)
    } catch (exception: Exception) {
        Log.e("safeApiCall", exception.stackTraceToString())
        Result.Error(exception)
    }
}