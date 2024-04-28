package com.luisfagundes.network.retrofit

import com.luisfagundes.model.NetworkPlayerStats
import com.luisfagundes.network.wrapper.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RetrofitSteamApi {
    @GET("ISteamUserStats/GetPlayerAchievements/v0001/")
    suspend fun getPlayerAchievements(
        @Query("steamid") steamId: Int,
        @Query("appId") appId: Int,
        @Query("l") language: Int? = null
    ): NetworkResponse<NetworkPlayerStats>
}