package com.luisfagundes.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OwnedGamesBodyResponse(val response: OwnedGamesResponse)

@Serializable
data class OwnedGamesResponse(val games: List<GameResponse>)

@Serializable
data class GameResponse(
    @SerialName("appid") val appId: Int,
    val name: String
)
