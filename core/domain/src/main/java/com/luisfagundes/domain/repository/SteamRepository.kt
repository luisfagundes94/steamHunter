package com.luisfagundes.domain.repository

import com.luisfagundes.domain.model.Achievement
import com.luisfagundes.domain.model.GameSchema
import com.luisfagundes.domain.model.OwnedGame
import com.luisfagundes.domain.model.PlayerStats
import kotlinx.coroutines.flow.Flow

interface SteamRepository {
    suspend fun getOwnedGames(steamId: String): Flow<List<OwnedGame>>
    suspend fun getPlayerAchievements(
        steamId: String,
        appId: Int,
    ): Flow<List<Achievement>>
    suspend fun getSchemaForGame(appId: Int): Flow<GameSchema>
}