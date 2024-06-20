package com.luisfagundes.domain.repository

import com.luisfagundes.model.DarkThemeConfig
import com.luisfagundes.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    val userData: Flow<UserData>

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
    suspend fun setUserSteamId(steamId: String)
}