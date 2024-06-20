package com.luisfagundes.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.luisfagundes.datasource.SteamDataSource
import com.luisfagundes.model.GameSchemaBodyResponse
import com.luisfagundes.model.RecentlyPlayedGamesBodyResponse
import com.luisfagundes.model.PlayerAchievementsResponse
import com.luisfagundes.network.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

private const val BASE_URL = BuildConfig.BACKEND_URL

@Singleton
class RetrofitSteamProvider @Inject constructor(
    json: Json,
    okHttpCallFactory: dagger.Lazy<Call.Factory>,
) : SteamDataSource {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .callFactory { okHttpCallFactory.get().newCall(it) }
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(RetrofitSteamApi::class.java)

    override suspend fun getRecentlyPlayedGames(steamId: String): RecentlyPlayedGamesBodyResponse =
        api.getRecentlyPlayedGames(steamId)

    override suspend fun getPlayer(
        steamId: String,
        appId: Int,
    ): PlayerAchievementsResponse = api.getPlayerAchievements(
        steamId = steamId,
        appId = appId,
    )

    override suspend fun getSchemaForGame(
        steamId: String,
        appId: Int
    ): GameSchemaBodyResponse = api.getSchemaForGame(
        steamId = steamId,
        appId = appId,
    )
}