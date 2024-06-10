package com.luisfagundes.model

data class Achievement(
    val apiName: String = "",
    val gameName: String = "",
    val name: String = "",
    val achieved: Boolean,
    val unlockDate: String,
    val description: String = "",
    val unlockedIconUrl: String = "",
    val lockedIconUrl: String = ""
)
