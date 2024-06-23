package com.luisfagundes.games.achievements.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.luisfagundes.games.achievements.presentation.AchievementsRoute
import kotlinx.serialization.Serializable

@Serializable
data class AchievementsRoute(
    val gameId: Int
)

fun NavGraphBuilder.achievementsScreen(
    onBackClick: () -> Unit
) {
    composable<AchievementsRoute> {
        AchievementsRoute(
            onBackClick = onBackClick
        )
    }
}
