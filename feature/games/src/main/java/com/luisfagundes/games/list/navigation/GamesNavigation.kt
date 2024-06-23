package com.luisfagundes.games.list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.luisfagundes.games.list.presentation.GamesRoute
import kotlinx.serialization.Serializable

@Serializable
object GamesNavigation

fun NavController.navigateToGames(navOptions: NavOptions) = navigate(GamesNavigation, navOptions)

fun NavGraphBuilder.gamesScreen(
    onGameClick: (Int) -> Unit
) {
    composable<GamesNavigation> {
        GamesRoute(
            onGameClick = onGameClick
        )
    }
}