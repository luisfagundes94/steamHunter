package com.luisfagundes.domain.repository

import com.luisfagundes.model.DarkThemeConfig
import com.luisfagundes.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    val userData: Flow<com.luisfagundes.model.UserData>

    suspend fun setDarkThemeConfig(darkThemeConfig: com.luisfagundes.model.DarkThemeConfig)
    suspend fun setUserSteamId(steamId: String)
}