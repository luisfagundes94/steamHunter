package com.luisfagundes.data.datasource

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import com.luisfagundes.model.DarkThemeConfig
import com.luisfagundes.model.UserData
import com.luisfagundes.steamhunter.data.DarkThemeConfigProto
import com.luisfagundes.steamhunter.data.UserPreferences
import com.luisfagundes.steamhunter.data.copy
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SteamHunterPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    val userData = userPreferences.data
        .map {
            UserData(
                darkThemeConfig = when (it.darkThemeConfig) {
                    DarkThemeConfigProto.DARK_THEME_CONFIG_UNSPECIFIED,
                    DarkThemeConfigProto.UNRECOGNIZED,
                    DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM -> DarkThemeConfig.FOLLOW_SYSTEM

                    DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT -> DarkThemeConfig.LIGHT
                    DarkThemeConfigProto.DARK_THEME_CONFIG_DARK -> DarkThemeConfig.DARK
                },
                steamId = it.steamId
            )
        }

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        userPreferences.updateData {
            it.copy {
                this.darkThemeConfig = when (darkThemeConfig) {
                    DarkThemeConfig.FOLLOW_SYSTEM -> DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM
                    DarkThemeConfig.LIGHT -> DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT
                    DarkThemeConfig.DARK -> DarkThemeConfigProto.DARK_THEME_CONFIG_DARK
                }
            }
        }
    }
    suspend fun setUserSteamId(id: String) {
        try {
            userPreferences.updateData {
                it.copy { this.steamId = id }
            }
        } catch (ioException: IOException) {
            Log.e("SteamHunterPreferences", "Failed to update user preferences", ioException)
        }
    }
}
