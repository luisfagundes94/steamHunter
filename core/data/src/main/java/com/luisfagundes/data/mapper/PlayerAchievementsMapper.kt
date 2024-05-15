package com.luisfagundes.data.mapper

import com.luisfagundes.domain.model.Achievement
import com.luisfagundes.model.AchievementResponse
import com.luisfagundes.model.PlayerAchievementsResponse
import com.luisfagundes.model.PlayerStatsResponse
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val UNLOCKED = 1
private const val DEFAULT_TIME_FORMAT = "dd/MM/yyyy"

internal object PlayerAchievementsMapper {
    fun PlayerStatsResponse.mapToDomain() = this.achievements.map { it.mapToDomain() }

    private fun AchievementResponse.mapToDomain() = Achievement(
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