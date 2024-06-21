package com.luisfagundes.domain.usecase

import com.luisfagundes.domain.repository.SteamRepository
import com.luisfagundes.domain.repository.UserDataRepository
import com.luisfagundes.result.Result
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetRecentlyPlayedGames @Inject constructor(
    private val steamRepository: SteamRepository,
    private val userDataRepository: UserDataRepository,
) {
    suspend operator fun invoke() = try {
        val steamId = userDataRepository.userData.first().steamId
        steamRepository.getGamesWithAchievements(steamId)
    } catch (e: Exception) {
        Result.Error(e)
    }
}