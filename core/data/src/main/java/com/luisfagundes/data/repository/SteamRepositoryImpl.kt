package com.luisfagundes.data.repository

import com.luisfagundes.data.mapper.GameSchemaMapper.mapToDomain
import com.luisfagundes.data.mapper.OwnedGamesMapper.mapToDomain
import com.luisfagundes.data.mapper.PlayerAchievementsMapper.mapToDomain
import com.luisfagundes.datasource.NetworkDataSource
import com.luisfagundes.domain.model.GameSchema
import com.luisfagundes.domain.repository.SteamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

internal class SteamRepositoryImpl @Inject constructor(
    private val network: NetworkDataSource
): SteamRepository {
    override suspend fun getOwnedGames(steamId: String) = flowOf(
        network.getOwnedGames(steamId).mapToDomain()
    )

    override suspend fun getPlayerAchievements(
        steamId: String,
        appId: Int
    ) = flowOf(
        network.getPlayerAchievements(
            steamId = steamId,
            appId = appId,
        ).playerStats.mapToDomain()
    )

    override suspend fun getSchemaForGame(appId: Int): Flow<GameSchema> = flowOf(
        network.getSchemaForGame(appId).mapToDomain()
    )
}