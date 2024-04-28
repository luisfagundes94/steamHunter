package com.luisfagundes.network.wrapper

import kotlinx.serialization.Serializable

@Serializable
internal data class NetworkResponse<T>(
    val data: T
)
