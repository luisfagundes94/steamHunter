package com.luisfagundes.domain.repository

import com.luisfagundes.domain.model.DarkThemeConfig
import com.luisfagundes.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    val userData: Flow<UserData>

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
}