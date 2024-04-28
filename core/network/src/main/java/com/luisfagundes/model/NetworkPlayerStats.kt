package com.luisfagundes.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPlayerStats(
    val steamId: String,
    val gameName: String,
    val success: Boolean,
    val achievements: List<NetworkAchievement>
)
