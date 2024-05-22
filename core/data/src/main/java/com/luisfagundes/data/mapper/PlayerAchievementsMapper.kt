package com.luisfagundes.data.mapper

import com.luisfagundes.domain.model.Achievement
import com.luisfagundes.domain.model.PlayerAchievements
import com.luisfagundes.domain.model.PlayerStats
import com.luisfagundes.model.AchievementResponse
import com.luisfagundes.model.PlayerAchievementsResponse
import com.luisfagundes.model.PlayerStatsResponse
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val UNLOCKED = 1
private const val DEFAULT_TIME_FORMAT = "dd/MM/yyyy"

internal object PlayerAchievementsMapper {

    fun PlayerAchievementsResponse.mapToDomain() = PlayerAchievements(
        playerStats = this.playerStats.mapToDomain()
    )
    private fun PlayerStatsResponse?.mapToDomain() = PlayerStats(
        success = this?.success,
        error = this?.error,
        achievements = this?.achievements?.map { it.mapToDomain() } ?: emptyList()
    )

    private fun AchievementResponse.mapToDomain() = Achievement(
        apiName = this.apiName,
        achieved = this.achieved == UNLOCKED,
        unlockDate = this.unlockTime.convertTimeStampToDateString()
    )

    private fun Long.convertTimeStampToDateString(
        dateFormat: String = DEFAULT_TIME_FORMAT
    ): String {
        val date = Date(this)
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        return formatter.format(date)
    }
}