package com.luisfagundes.datasource

import com.luisfagundes.model.GameSchemaBodyResponse
import com.luisfagundes.model.PlayerAchievementsResponse
import com.luisfagundes.model.RecentlyPlayedGamesBodyResponse

interface SteamDataSource {
    suspend fun getRecentlyPlayedGames(steamId: String): RecentlyPlayedGamesBodyResponse
    suspend fun getPlayer(
        steamId: String,
        appId: Int,
    ): PlayerAchievementsResponse
    suspend fun getSchemaForGame(
        steamId: String,
        appId: Int
    ): GameSchemaBodyResponse
}
