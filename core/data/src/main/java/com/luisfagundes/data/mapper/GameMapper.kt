package com.luisfagundes.data.mapper

import com.luisfagundes.model.Game
import com.luisfagundes.model.GameResponse
import com.luisfagundes.model.RecentlyPlayedGamesBodyResponse

private const val IMAGE_BASE_URL = "https://media.steampowered.com/steamcommunity/public/images/apps/"

object GameMapper {
    fun RecentlyPlayedGamesBodyResponse.mapToDomain() = this.response.games.map { it.mapToDomain() }

    private fun GameResponse.mapToDomain() = com.luisfagundes.model.Game(
        appId = this.appId,
        name = this.name,
        achievementsUnlocked = 0,
        achievementsTotal = 0,
        imageUrl = IMAGE_BASE_URL + this.appId + "/${this.iconHash}.jpg"
    )
}