package com.luisfagundes.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecentlyPlayedGamesBodyResponse(
    val response: RecentlyPlayedGamesResponse
)

@Serializable
data class RecentlyPlayedGamesResponse(
    @SerialName("total_count") val totalCount: Int,
    @SerialName("games") val games: List<GameResponse>
)

@Serializable
data class GameResponse(
    @SerialName("appid") val appId: Int,
    @SerialName("img_icon_url") val iconHash: String,
    val name: String
)
