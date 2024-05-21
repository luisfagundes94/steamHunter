package com.luisfagundes.domain.model

data class PlayerStats(
    val success: Boolean? = false,
    val error: String? = null,
    val achievements: List<Achievement> = emptyList()
)
