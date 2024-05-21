package com.luisfagundes.domain.repository

import com.luisfagundes.domain.model.GameSchema
import com.luisfagundes.domain.model.Game
import com.luisfagundes.domain.model.PlayerAchievements
import com.luisfagundes.result.Result

interface SteamRepository {
    suspend fun getRecentlyPlayedGames(steamId: String): Result<List<Game>>
    suspend fun getPlayerAchievements(
        steamId: String,
        appId: Int,
    ): Result<PlayerAchievements?>
    suspend fun getSchemaForGame(appId: Int): Result<GameSchema>
}