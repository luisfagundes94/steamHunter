package com.luisfagundes.data.mapper

import com.luisfagundes.model.AchievementSchema
import com.luisfagundes.model.AvailableGameStats
import com.luisfagundes.model.GameSchema
import com.luisfagundes.model.AchievementSchemaResponse
import com.luisfagundes.model.AvailableGameStatsResponse
import com.luisfagundes.model.GameSchemaBodyResponse
import com.luisfagundes.model.GameSchemaResponse

object GameSchemaMapper {
    fun GameSchemaBodyResponse.mapToDomain() = this.game.mapToDomain()

    private fun GameSchemaResponse.mapToDomain() = com.luisfagundes.model.GameSchema(
        gameName = this.gameName,
        availableGameStats = this.availableGameStats.mapToDomain()
    )

    private fun AvailableGameStatsResponse.mapToDomain() =
        com.luisfagundes.model.AvailableGameStats(
            achievements = this.achievements.map { it.mapToDomain() }
        )

    private fun AchievementSchemaResponse.mapToDomain() = com.luisfagundes.model.AchievementSchema(
        name = this.name,
        displayName = this.displayName,
        description = this.description,
        iconUrl = this.icon,
        grayIconUrl = this.iconGray
    )
}