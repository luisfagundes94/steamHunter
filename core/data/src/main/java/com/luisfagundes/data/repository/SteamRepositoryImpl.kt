package com.luisfagundes.data.repository

import com.luisfagundes.Dispatcher
import com.luisfagundes.SteamHunterDispatchers.IO
import com.luisfagundes.data.mapper.GameMapperImpl.mapToDomain
import com.luisfagundes.data.mapper.GameMapperImpl.mergeWith
import com.luisfagundes.data.mapper.GameSchemaMapper.mapToDomain
import com.luisfagundes.data.mapper.GameSchemaMapper.mergeWith
import com.luisfagundes.data.mapper.PlayerAchievementsMapper.mapToDomain
import com.luisfagundes.datasource.SteamDataSource
import com.luisfagundes.domain.repository.SteamRepository
import com.luisfagundes.model.Game
import com.luisfagundes.result.safeAsyncRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import javax.inject.Inject

internal class SteamRepositoryImpl @Inject constructor(
    private val steamDataSource: SteamDataSource,
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher,
) : SteamRepository {

    override suspend fun getGamesWithAchievements(
        steamId: String
    ) = safeAsyncRequest(dispatcher) {
        val gamesWithAchievements = mutableListOf<Game>()
        val games = steamDataSource.getRecentlyPlayedGames(steamId).mapToDomain()

        games.forEach { game ->
            async {
                val achievements = getPlayer(steamId, game).stats?.achievements
                gamesWithAchievements.add(game.mergeWith(achievements))
            }.await()
        }

        gamesWithAchievements
    }

    override suspend fun getAchievements(
        steamId: String,
        appId: Int
    ) = safeAsyncRequest(dispatcher) {
        val player = steamDataSource.getPlayer(steamId, appId).mapToDomain()
        val gameSchema = steamDataSource.getSchemaForGame(steamId, appId).mapToDomain()
        val achievements = player.stats?.achievements

        achievements?.mergeWith(gameSchema) ?: emptyList()
    }

    private suspend fun getPlayer(
        steamId: String,
        game: Game
    ) = steamDataSource.getPlayer(
        steamId = steamId,
        appId = game.appId
    ).mapToDomain()
}