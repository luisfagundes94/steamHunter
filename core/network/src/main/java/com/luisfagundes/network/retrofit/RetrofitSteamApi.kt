package com.luisfagundes.network.retrofit

import com.luisfagundes.model.GameSchemaBodyResponse
import com.luisfagundes.model.OwnedGamesBodyResponse
import com.luisfagundes.model.PlayerAchievementsResponse
import com.luisfagundes.network.wrapper.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RetrofitSteamApi {
    @GET("IPlayerService/GetOwnedGames/v1/")
    suspend fun getOwnedGames(
        @Query("steamid") steamId: String,
        @Query("include_appinfo") includeAppInfo: Int = 1
    ): NetworkResponse<OwnedGamesBodyResponse>

    @GET("ISteamUserStats/GetPlayerAchievements/v1/")
    suspend fun getPlayerAchievements(
        @Query("steamid") steamId: String,
        @Query("appid") appId: Int
    ): NetworkResponse<PlayerAchievementsResponse>

    @GET("ISteamUserStats/GetSchemaForGame/v2/")
    suspend fun getSchemaForGame(
        @Query("appid") appId: Int
    ): NetworkResponse<GameSchemaBodyResponse>
}