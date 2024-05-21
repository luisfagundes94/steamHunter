package com.luisfagundes.games.achievements.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.luisfagundes.games.achievements.presentation.AchievementsRoute

const val ACHIEVEMENTS_ROUTE = "achievements/"

fun NavController.navigateToAchievements(navOptions: NavOptions) = navigate(ACHIEVEMENTS_ROUTE, navOptions)

fun NavGraphBuilder.achievementsScreen(
    onGameClick: (String) -> Unit
) {
    composable(
        route = ACHIEVEMENTS_ROUTE,
    ) {
        AchievementsRoute(
            onBackClick = {}
        )
    }
}