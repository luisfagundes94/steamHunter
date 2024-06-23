package com.luisfagundes.data.mapper

import com.luisfagundes.model.Achievement
import com.luisfagundes.model.AchievementSchema
import com.luisfagundes.model.AchievementSchemaResponse
import com.luisfagundes.model.AvailableGameStats
import com.luisfagundes.model.AvailableGameStatsResponse
import com.luisfagundes.model.GameSchema
import com.luisfagundes.model.GameSchemaBodyResponse
import com.luisfagundes.model.GameSchemaResponse

object GameSchemaMapper {
    fun List<Achievement>.mergeWith(gameSchema: GameSchema): List<Achievement> {
        val achievementSchema = gameSchema.availableGameStats.achievements
        val achievementSchemaMap = achievementSchema.associateBy { it.name }

        return this.map { achievement ->
            val schema = achievementSchemaMap[achievement.apiName]
            achievement.copy(
                gameName = gameSchema.gameName,
                name = schema?.displayName ?: "",
                description = schema?.description ?: "",
                unlockedIconUrl = schema?.iconUrl ?: "",
                lockedIconUrl = schema?.grayIconUrl ?: ""
            )
        }
    }

    fun GameSchemaBodyResponse.mapToDomain() = this.game.mapToDomain()

    private fun GameSchemaResponse.mapToDomain() = GameSchema(
        gameName = this.gameName,
        availableGameStats = this.availableGameStats.mapToDomain()
    )

    private fun AvailableGameStatsResponse.mapToDomain() =
        AvailableGameStats(
            achievements = this.achievements.map { it.mapToDomain() }
        )

    private fun AchievementSchemaResponse.mapToDomain() = AchievementSchema(
        name = this.name,
        displayName = this.displayName,
        description = this.description,
        iconUrl = this.icon,
        grayIconUrl = this.iconGray
    )
}
