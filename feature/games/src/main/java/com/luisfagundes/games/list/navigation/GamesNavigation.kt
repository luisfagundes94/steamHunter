package com.luisfagundes.games.list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.luisfagundes.games.list.presentation.GamesRoute

const val GAMES_ROUTE = "games_route/"

fun NavController.navigateToGames(navOptions: NavOptions) = navigate(GAMES_ROUTE, navOptions)

fun NavGraphBuilder.gamesScreen(
    onGameClick: (String) -> Unit
) {
    composable(
        route = GAMES_ROUTE,
    ) {
        GamesRoute(
            onGameClick = onGameClick
        )
    }
}