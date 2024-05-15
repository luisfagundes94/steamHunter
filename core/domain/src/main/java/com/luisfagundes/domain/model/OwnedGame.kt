package com.luisfagundes.domain.model

data class OwnedGame(
    val appId: Int,
    val name: String,
    var achievementsUnlocked: Int,
    var achievementsTotal: Int,
    var imageUrl: String = "",
)