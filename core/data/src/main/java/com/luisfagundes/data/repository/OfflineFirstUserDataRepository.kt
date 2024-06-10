package com.luisfagundes.data.repository

import com.luisfagundes.data.datasource.SteamHunterPreferencesDataSource
import com.luisfagundes.domain.repository.UserDataRepository
import com.luisfagundes.model.DarkThemeConfig
import com.luisfagundes.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class OfflineFirstUserDataRepository @Inject constructor(
    private val steamHunterPreferencesDataSource: SteamHunterPreferencesDataSource,
) : UserDataRepository {
    override val userData: Flow<com.luisfagundes.model.UserData> =
        steamHunterPreferencesDataSource.userData

    override suspend fun setDarkThemeConfig(darkThemeConfig: com.luisfagundes.model.DarkThemeConfig) {
        steamHunterPreferencesDataSource.setDarkThemeConfig(darkThemeConfig)
    }

    override suspend fun setUserSteamId(steamId: String) {
        steamHunterPreferencesDataSource.setUserSteamId(steamId)
    }
}