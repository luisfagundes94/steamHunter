package com.luisfagundes.data.repository

import com.luisfagundes.data.mapper.GameSchemaMapper.mapToDomain
import com.luisfagundes.data.mapper.GameMapper.mapToDomain
import com.luisfagundes.data.mapper.PlayerAchievementsMapper.mapToDomain
import com.luisfagundes.datasource.NetworkDataSource
import com.luisfagundes.result.Result
import com.luisfagundes.domain.repository.SteamRepository
import com.luisfagundes.result.safeApiCall
import javax.inject.Inject

internal class SteamRepositoryImpl @Inject constructor(
    private val network: NetworkDataSource
) : SteamRepository {
    override suspend fun getRecentlyPlayedGames(steamId: String) = safeApiCall {
        network.getRecentlyPlayedGames(steamId).mapToDomain()
    }

    override suspend fun getPlayerAchievements(
        steamId: String,
        appId: Int
    ) = try {
        val data = network.getPlayerAchievements(
            steamId = steamId,
            appId = appId,
        ).mapToDomain()
        Result.Success(data)
    } catch (e: Exception) {
        Result.Success(null)
    }

    override suspend fun getSchemaForGame(
        steamId: String,
        appId: Int
    ) = safeApiCall {
        network.getSchemaForGame(
            steamId = steamId,
            appId = appId,
        ).mapToDomain()
    }
}