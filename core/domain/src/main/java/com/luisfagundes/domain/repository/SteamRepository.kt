package com.luisfagundes.domain.repository

import com.luisfagundes.domain.model.PlayerStats
import kotlinx.coroutines.flow.Flow

interface SteamRepository {
    suspend fun getPlayerStats(
        steamId: Int,
        appId: Int,
        language: Int? = null
    ): Flow<PlayerStats>
}