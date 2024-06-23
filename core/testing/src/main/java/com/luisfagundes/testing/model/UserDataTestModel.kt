package com.luisfagundes.testing.model

import com.luisfagundes.model.DarkThemeConfig
import com.luisfagundes.model.UserData

const val STEAM_ID = "123"

val userData = UserData(
    darkThemeConfig = DarkThemeConfig.DARK,
    useDynamicColor = false,
    steamId = STEAM_ID
)
