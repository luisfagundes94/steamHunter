package com.luisfagundes.data.repository

import com.luisfagundes.data.datasource.SteamHunterPreferencesDataSource
import com.luisfagundes.model.DarkThemeConfig
import com.luisfagundes.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class OfflineFirstUserDataRepository @Inject constructor(
    private val steamHunterPreferencesDataSource: SteamHunterPreferencesDataSource,
) : UserDataRepository {
    override val userData: Flow<UserData> =
        steamHunterPreferencesDataSource.userData

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        steamHunterPreferencesDataSource.setDarkThemeConfig(darkThemeConfig)
    }
}