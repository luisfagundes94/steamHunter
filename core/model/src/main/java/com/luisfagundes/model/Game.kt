package com.luisfagundes.model

data class Game(
    val appId: Int,
    val name: String,
    var achievementsUnlocked: Int,
    var achievementsTotal: Int,
    var imageUrl: String = "",
)
