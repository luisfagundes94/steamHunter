package com.luisfagundes.data.mapper

import com.luisfagundes.data.mapper.PlayerStatsMapper.mapToDomain
import com.luisfagundes.domain.model.Achievement
import com.luisfagundes.domain.model.PlayerStats
import com.luisfagundes.model.NetworkAchievement
import com.luisfagundes.model.NetworkPlayerStats
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val UNLOCKED = 1

internal object PlayerStatsMapper {
    fun NetworkPlayerStats.mapToDomain() = PlayerStats(
        gameName = this.gameName,
        achievements = this.achievements.map { achievement -> achievement.mapToDomain() }
    )

    private fun NetworkAchievement.mapToDomain() = Achievement(
        name = this.name ?: "",
        achieved = this.achieved == UNLOCKED,
        unlockDate = this.unlocktime.convertTimeStampToDateString()
    )

    private fun Long.convertTimeStampToDateString(
        dateFormat: String = "dd/MM/yyyy"
    ): String {
        val date = Date(this)
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        return formatter.format(date)
    }
}