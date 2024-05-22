package com.luisfagundes.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameSchemaBodyResponse(val game: GameSchemaResponse)

@Serializable
data class GameSchemaResponse(
    val gameName: String,
    val availableGameStats: AvailableGameStatsResponse
)

@Serializable
data class AvailableGameStatsResponse(val achievements: List<AchievementSchemaResponse>)

@Serializable
data class AchievementSchemaResponse(
    val name: String,
    val displayName: String,
    val description: String = "",
    val icon: String,
    @SerialName("icongray") val iconGray: String
)