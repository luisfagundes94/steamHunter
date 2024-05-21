package com.luisfagundes.datasource

import com.luisfagundes.model.GameSchemaBodyResponse
import com.luisfagundes.model.RecentlyPlayedGamesBodyResponse
import com.luisfagundes.model.PlayerAchievementsResponse

interface NetworkDataSource {
    suspend fun getRecentlyPlayedGames(steamId: String): RecentlyPlayedGamesBodyResponse
    suspend fun getPlayerAchievements(
        steamId: String,
        appId: Int,
    ): PlayerAchievementsResponse
    suspend fun getSchemaForGame(appId: Int): GameSchemaBodyResponse
}