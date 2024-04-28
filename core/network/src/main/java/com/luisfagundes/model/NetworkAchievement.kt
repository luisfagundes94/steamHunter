package com.luisfagundes.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkAchievement(
    val apiname: String,
    val achieved: Int,
    val unlocktime: Long
)
