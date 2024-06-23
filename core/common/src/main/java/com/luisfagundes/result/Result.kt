package com.luisfagundes.result

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable) : Result<Nothing>
}

fun <T> Result<T>.getResultOrThrow(): T {
    return when (this) {
        is Result.Success -> this.data
        is Result.Error -> throw this.exception
    }
}

fun <T> Result<T>.getResultOrNull(): T? {
    return when (this) {
        is Result.Success -> this.data
        is Result.Error -> null
    }
}

fun <T> Result<T>.hasError(): Boolean {
    return when (this) {
        is Result.Success -> false
        is Result.Error -> true
    }
}
