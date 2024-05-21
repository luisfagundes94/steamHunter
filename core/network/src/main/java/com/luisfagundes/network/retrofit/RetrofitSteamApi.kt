package com.luisfagundes.network.retrofit

import com.luisfagundes.model.GameSchemaBodyResponse
import com.luisfagundes.model.RecentlyPlayedGamesBodyResponse
import com.luisfagundes.model.PlayerAchievementsResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RetrofitSteamApi {
    @GET("IPlayerService/GetRecentlyPlayedGames/v1/")
    suspend fun getRecentlyPlayedGames(
        @Query("steamid") steamId: String,
        @Query("include_appinfo") includeAppInfo: Int = 1
    ): RecentlyPlayedGamesBodyResponse

    @GET("ISteamUserStats/GetPlayerAchievements/v1/")
    suspend fun getPlayerAchievements(
        @Query("steamid") steamId: String,
        @Query("appid") appId: Int
    ): PlayerAchievementsResponse

    @GET("ISteamUserStats/GetSchemaForGame/v2/")
    suspend fun getSchemaForGame(
        @Query("appid") appId: Int
    ): GameSchemaBodyResponse
}