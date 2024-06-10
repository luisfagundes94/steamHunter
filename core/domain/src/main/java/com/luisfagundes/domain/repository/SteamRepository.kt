package com.luisfagundes.domain.repository

import com.luisfagundes.model.GameSchema
import com.luisfagundes.model.Game
import com.luisfagundes.model.PlayerAchievements
import com.luisfagundes.result.Result

interface SteamRepository {
    suspend fun getRecentlyPlayedGames(steamId: String): Result<List<com.luisfagundes.model.Game>>
    suspend fun getPlayerAchievements(
        steamId: String,
        appId: Int,
    ): Result<com.luisfagundes.model.PlayerAchievements?>
    suspend fun getSchemaForGame(
        steamId: String,
        appId: Int
    ): Result<com.luisfagundes.model.GameSchema>
}