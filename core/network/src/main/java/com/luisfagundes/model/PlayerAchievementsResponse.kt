package com.luisfagundes.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerAchievementsResponse(
    @SerialName("playerstats") val playerStats: PlayerStats
)

@Serializable
data class PlayerStats(val achievements: List<Achievement>)

@Serializable
data class Achievement(
    @SerialName("apiname") val apiName: String,
    val achieved: Int
)
