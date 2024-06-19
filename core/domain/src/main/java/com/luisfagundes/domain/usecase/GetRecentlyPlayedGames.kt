package com.luisfagundes.domain.usecase

import com.luisfagundes.Dispatcher
import com.luisfagundes.SteamHunterDispatchers
import com.luisfagundes.model.Game
import com.luisfagundes.domain.repository.SteamRepository
import com.luisfagundes.domain.repository.UserDataRepository
import com.luisfagundes.result.Result
import com.luisfagundes.result.getResultOrNull
import com.luisfagundes.result.getResultOrThrow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRecentlyPlayedGames @Inject constructor(
    private val steamRepository: SteamRepository,
    private val userDataRepository: UserDataRepository,
    @Dispatcher(SteamHunterDispatchers.IO) private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(): Result<List<Game>> = withContext(dispatcher) {
        try {
            val steamId = userDataRepository.userData.first().steamId
            val gamesWithAchievements = mutableListOf<Game>()
            val games = steamRepository.getRecentlyPlayedGames(steamId).getResultOrThrow()

            games.forEach { game ->
                async {
                    val playerAchievementsResult = steamRepository.getPlayerAchievements(
                        steamId,
                        game.appId
                    )

                    val playerAchievements = playerAchievementsResult.getResultOrNull() ?: return@async
                    val achievements = playerAchievements.playerStats?.achievements

                    gamesWithAchievements.add(
                        Game(
                            appId = game.appId,
                            name = game.name,
                            achievementsUnlocked = achievements?.count { it.achieved } ?: 0,
                            achievementsTotal = achievements?.size ?: 0,
                            imageUrl = game.imageUrl
                        )
                    )
                }.await()
            }
            Result.Success(gamesWithAchievements)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}