package com.luisfagundes.data.mapper

import com.luisfagundes.domain.model.OwnedGame
import com.luisfagundes.model.GameResponse
import com.luisfagundes.model.OwnedGamesBodyResponse

object OwnedGamesMapper {
    fun OwnedGamesBodyResponse.mapToDomain() = this.response.games.map { it.mapToDomain() }

    private fun GameResponse.mapToDomain() = OwnedGame(
        appId = this.appId,
        name = this.name,
    )
}