package com.luisfagundes.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.luisfagundes.datasource.NetworkDataSource
import com.luisfagundes.model.PlayerStatsResponse
import com.luisfagundes.network.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

private const val BASE_URL = BuildConfig.BACKEND_URL

@Singleton
internal class RetrofitNetworkProvider @Inject constructor(
    json: Json,
    okHttpCallFactory: dagger.Lazy<Call.Factory>,
): NetworkDataSource {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .callFactory { okHttpCallFactory.get().newCall(it) }
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(RetrofitSteamApi::class.java)

    override suspend fun getPlayerAchievements(
        steamId: Int,
        appId: Int,
        language: Int?
    ): PlayerStatsResponse =
        api.getPlayerAchievements(
            steamId = steamId,
            appId = appId,
            language = language
        ).data


}