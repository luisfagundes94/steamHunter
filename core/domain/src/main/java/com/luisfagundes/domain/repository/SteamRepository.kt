package com.luisfagundes.domain.repository

import com.luisfagundes.model.Achievement
import com.luisfagundes.model.Game
import com.luisfagundes.result.Result

interface SteamRepository {
    suspend fun getGamesWithAchievements(steamId: String): Result<List<Game>>
    suspend fun getAchievements(steamId: String, appId: Int): Result<List<Achievement>>
}
