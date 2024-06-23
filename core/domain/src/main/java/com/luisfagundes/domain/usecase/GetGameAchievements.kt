package com.luisfagundes.domain.usecase

import com.luisfagundes.domain.repository.SteamRepository
import com.luisfagundes.domain.repository.UserDataRepository
import com.luisfagundes.result.Result
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

class GetGameAchievements @Inject constructor(
    private val steamRepository: SteamRepository,
    private val userRepository: UserDataRepository
) {
    suspend operator fun invoke(appId: Int) = try {
        val steamId = userRepository.userData.first().steamId
        steamRepository.getAchievements(steamId, appId)
    } catch (exception: Exception) {
        Timber.tag("GetGameAchievements").e(exception.stackTraceToString())
        Result.Error(exception)
    }
}
