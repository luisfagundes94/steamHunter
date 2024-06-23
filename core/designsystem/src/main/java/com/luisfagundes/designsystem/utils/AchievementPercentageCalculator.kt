package com.luisfagundes.designsystem.utils

private const val MAX_PERCENTAGE = 100

fun calculatePercentage(total: Int, unlocked: Int) =
    if (total > 0) {
        (unlocked.toFloat() / total.toFloat() * MAX_PERCENTAGE).toInt()
    } else {
        0
    }
