package com.luisfagundes.steamhunter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.luisfagundes.about.details.navigation.AboutItemNavigation
import com.luisfagundes.about.details.navigation.aboutItemScreen
import com.luisfagundes.about.list.navigation.aboutScreen
import com.luisfagundes.games.achievements.navigation.AchievementsNavigation
import com.luisfagundes.games.achievements.navigation.achievementsScreen
import com.luisfagundes.games.list.navigation.GamesNavigation
import com.luisfagundes.games.list.navigation.gamesScreen
import com.luisfagundes.profile.navigation.profileScreen
import com.luisfagundes.search.navigation.searchScreen
import com.luisfagundes.steamhunter.ui.SteamHunterAppState

@Composable
fun SteamHunterNavHost(
    appState: SteamHunterAppState,
    modifier: Modifier = Modifier,
    startDestination: GamesNavigation = GamesNavigation,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        // Top Level Destinations
        gamesScreen(
            onGameClick = { gameId -> navController.navigate(AchievementsNavigation(gameId)) }
        )
        profileScreen()
        aboutScreen(
            onItemClick = { descriptionResId ->
                navController.navigate(AboutItemNavigation(descriptionResId))
            }
        )

        // Other
        searchScreen()
        achievementsScreen(
            onBackClick = navController::popBackStack
        )
        aboutItemScreen(
            onBackClick = navController::popBackStack
        )
    }
}
