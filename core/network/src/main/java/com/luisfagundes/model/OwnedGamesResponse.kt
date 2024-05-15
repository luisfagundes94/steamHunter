package com.luisfagundes.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OwnedGamesResponse(val response: OwnedGames)

@Serializable
data class OwnedGames(val games: List<Game>)

@Serializable
data class Game(
    @SerialName("appid") val appId: Int,
    val name: String
)
