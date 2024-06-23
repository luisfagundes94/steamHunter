package com.luisfagundes.data.mapper

import com.luisfagundes.domain.mapper.GameMapper
import com.luisfagundes.model.Achievement
import com.luisfagundes.model.Game
import com.luisfagundes.model.GameResponse
import com.luisfagundes.model.RecentlyPlayedGamesBodyResponse

private const val IMAGE_BASE_URL = "https://media.steampowered.com/steamcommunity/public/images/apps/"

object GameMapperImpl : GameMapper {

    override fun Game.mergeWith(achievements: List<Achievement>?): Game {
        return Game(
            appId = this.appId,
            name = this.name,
            achievementsUnlocked = achievements?.count { it.achieved } ?: 0,
            achievementsTotal = achievements?.size ?: 0,
            imageUrl = this.imageUrl
        )
    }

    fun RecentlyPlayedGamesBodyResponse.mapToDomain() = this.response.games.map {
        it.mapToDomain()
    }

    private fun GameResponse.mapToDomain() = Game(
        appId = this.appId,
        name = this.name,
        achievementsUnlocked = 0,
        achievementsTotal = 0,
        imageUrl = IMAGE_BASE_URL + this.appId + "/${this.iconHash}.jpg"
    )
}
