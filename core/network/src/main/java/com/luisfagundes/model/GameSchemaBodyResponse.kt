package com.luisfagundes.model

import kotlinx.serialization.Serializable

@Serializable
data class GameSchemaBodyResponse(val game: GameSchemaResponse)

@Serializable
data class GameSchemaResponse(val availableGameStats: AvailableGameStatsResponse)

@Serializable
data class AvailableGameStatsResponse(val achievements: List<AchievementSchemaResponse>)

@Serializable
data class AchievementSchemaResponse(
    val name: String,
    val displayName: String,
    val description: String
)