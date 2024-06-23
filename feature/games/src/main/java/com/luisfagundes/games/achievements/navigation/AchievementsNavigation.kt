package com.luisfagundes.games.achievements.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.luisfagundes.games.achievements.presentation.AchievementsRoute
import kotlinx.serialization.Serializable

@Serializable
data class AchievementsNavigation(
    val gameId: Int
)

fun NavGraphBuilder.achievementsScreen(
    onBackClick: () -> Unit
) {
    composable<AchievementsNavigation> {
        AchievementsRoute(
            onBackClick = onBackClick
        )
    }
}
