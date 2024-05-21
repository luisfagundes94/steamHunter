package com.luisfagundes.domain.usecase

import com.luisfagundes.domain.model.Game
import com.luisfagundes.domain.repository.SteamRepository
import com.luisfagundes.result.Result
import com.luisfagundes.result.getResultOrNull
import com.luisfagundes.result.getResultOrThrow
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class GetGameAchievements @Inject constructor(
    private val repository: SteamRepository,
) {
    suspend operator fun invoke(
        steamId: String
    ): Result<List<Game>> = runBlocking {
        try {
            val gamesWithAchievements = mutableListOf<Game>()
            val games = repository.getRecentlyPlayedGames(steamId).getResultOrThrow()

            games.forEach { game ->
                async {
                    val playerAchievementsResult = repository.getPlayerAchievements(
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