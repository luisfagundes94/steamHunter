package com.luisfagundes.domain.usecase

import com.luisfagundes.domain.repository.SteamRepository
import com.luisfagundes.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetGameAchievements @Inject constructor(
    private val steamRepository: SteamRepository,
    private val userRepository: UserDataRepository
) {
    suspend operator fun invoke(appId: Int) = steamRepository.getPlayerAchievements(
        steamId = userRepository.userData.first().steamId,
        appId = appId
    )
}