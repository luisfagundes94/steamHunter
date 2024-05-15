package com.luisfagundes.designsystem.utils

fun calculatePercentage(total: Int, unlocked: Int) =
    if (total > 0) {
        (unlocked.toFloat() / total.toFloat() * 100).toInt()
    } else {
        0
    }