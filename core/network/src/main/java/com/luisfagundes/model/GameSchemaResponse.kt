package com.luisfagundes.model

import kotlinx.serialization.Serializable

@Serializable
data class GameSchemaResponse(val game: GameSchema)

@Serializable
data class GameSchema(val availableGameStats: AvailableGameStats)

@Serializable
data class AvailableGameStats(val achievements: List<AchievementSchema>)

@Serializable
data class AchievementSchema(
    val name: String,
    val displayName: String,
    val description: String
)