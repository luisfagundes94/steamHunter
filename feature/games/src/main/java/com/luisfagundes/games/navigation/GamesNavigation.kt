package com.luisfagundes.games.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val GAMES_ROUTE = "games_route/"

fun NavController.navigateToGames(navOptions: NavOptions) = navigate(GAMES_ROUTE, navOptions)

fun NavGraphBuilder.gamesScreen() {
    composable(
        route = GAMES_ROUTE,
    ) {
        // GamesScreen()
    }
}