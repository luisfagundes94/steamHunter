package com.luisfagundes.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerAchievementsResponse(
    @SerialName("playerstats") val playerStats: PlayerStatsResponse,
)

@Serializable
data class PlayerStatsResponse(
    val error: String? = null,
    val success: Boolean,
    val achievements: List<AchievementResponse>? = null
)

@Serializable
data class AchievementResponse(
    @SerialName("apiname") val apiName: String,
    @SerialName("unlocktime") val unlockTime: Long,
    val achieved: Int
)
