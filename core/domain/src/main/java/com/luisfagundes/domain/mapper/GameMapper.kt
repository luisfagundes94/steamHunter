package com.luisfagundes.domain.mapper

import com.luisfagundes.model.Achievement
import com.luisfagundes.model.Game

interface GameMapper {
    fun Game.mergeWith(achievements: List<Achievement>?): Game
}
