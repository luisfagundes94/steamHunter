package com.luisfagundes.datasource

import com.luisfagundes.model.GameSchemaBodyResponse
import com.luisfagundes.model.OwnedGamesBodyResponse
import com.luisfagundes.model.PlayerAchievementsResponse

interface NetworkDataSource {
    suspend fun getOwnedGames(steamId: String): OwnedGamesBodyResponse
    suspend fun getPlayerAchievements(
        steamId: String,
        appId: Int,
    ): PlayerAchievementsResponse
    suspend fun getSchemaForGame(appId: Int): GameSchemaBodyResponse
}