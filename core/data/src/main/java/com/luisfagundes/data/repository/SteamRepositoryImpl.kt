package com.luisfagundes.data.repository

import com.luisfagundes.data.mapper.PlayerStatsMapper.mapToDomain
import com.luisfagundes.datasource.NetworkDataSource
import com.luisfagundes.domain.model.PlayerStats
import com.luisfagundes.domain.repository.SteamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SteamRepositoryImpl @Inject constructor(
    private val network: NetworkDataSource
): SteamRepository {
    override suspend fun getPlayerStats(
        steamId: Int,
        appId: Int,
        language: Int?
    ): Flow<PlayerStats> = flowOf(
        network.getPlayerAchievements(
            steamId = steamId,
            appId = appId,
            language = language
        )
    ).map { networkPlayerStats -> networkPlayerStats.mapToDomain() }
}