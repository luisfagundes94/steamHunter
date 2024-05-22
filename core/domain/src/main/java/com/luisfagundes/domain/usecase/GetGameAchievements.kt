package com.luisfagundes.domain.usecase

import android.util.Log
import com.luisfagundes.domain.model.Achievement
import com.luisfagundes.domain.repository.SteamRepository
import com.luisfagundes.domain.repository.UserDataRepository
import com.luisfagundes.result.Result
import com.luisfagundes.result.getResultOrThrow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class GetGameAchievements @Inject constructor(
    private val steamRepository: SteamRepository,
    private val userRepository: UserDataRepository
) {
    suspend operator fun invoke(appId: Int): Result<List<Achievement>?> = runBlocking {
        try {
            val steamId = userRepository.userData.first().steamId

            val playerAchievements = steamRepository.getPlayerAchievements(
                steamId = steamId,
                appId = appId
            ).getResultOrThrow()
            val gameSchema = steamRepository.getSchemaForGame(
                steamId = steamId,
                appId = appId
            ).getResultOrThrow()

            val achievementSchema = gameSchema.availableGameStats.achievements
            val achievementSchemaMap = achievementSchema.associateBy { it.name }

            val data = playerAchievements?.playerStats?.achievements?.map { achievement ->
                val schema = achievementSchemaMap[achievement.apiName]
                achievement.copy(
                    gameName = gameSchema.gameName,
                    name = schema?.displayName ?: "",
                    description = schema?.description ?: "",
                    unlockedIconUrl = schema?.iconUrl ?: "",
                    lockedIconUrl = schema?.grayIconUrl ?: ""
                )
            }
            Result.Success(data)
        } catch (e: Exception) {
            Log.e("GetGameAchievements", e.stackTraceToString())
            Result.Error(e)
        }
    }
}