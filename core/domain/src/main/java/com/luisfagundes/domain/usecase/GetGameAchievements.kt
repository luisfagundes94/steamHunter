package com.luisfagundes.domain.usecase

import android.util.Log
import com.luisfagundes.model.Achievement
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
    suspend operator fun invoke(appId: Int): Result<List<Achievement>?> {
        return try {
            val steamId = userRepository.userData.first().steamId
            steamRepository.getAchievements(steamId, appId)
        } catch (e: Exception) {
            Log.e("GetGameAchievements", e.stackTraceToString())
            Result.Error(e)
        }
    }
}