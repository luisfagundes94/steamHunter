package com.luisfagundes.datasource

import com.luisfagundes.model.PlayerStatsResponse

interface NetworkDataSource {
    suspend fun getPlayerAchievements(
        steamId: Int,
        appId: Int,
        language: Int? = null
    ): PlayerStatsResponse
}