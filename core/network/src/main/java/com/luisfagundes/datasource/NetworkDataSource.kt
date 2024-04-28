package com.luisfagundes.datasource

import com.luisfagundes.model.NetworkPlayerStats

interface NetworkDataSource {
    suspend fun getPlayerAchievements(
        steamId: Int,
        appId: Int,
        language: Int? = null
    ): NetworkPlayerStats
}