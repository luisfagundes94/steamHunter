package com.luisfagundes.steamhunter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.luisfagundes.about.navigation.aboutScreen
import com.luisfagundes.games.navigation.GAMES_ROUTE
import com.luisfagundes.games.navigation.gamesScreen
import com.luisfagundes.profile.navigation.profileScreen
import com.luisfagundes.search.navigation.searchScreen
import com.luisfagundes.steamhunter.ui.SteamHunterAppState

@Composable
fun SteamHunterNavHost(
    appState: SteamHunterAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = GAMES_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        gamesScreen(
            onGameClick = { }
        )
        profileScreen()
        aboutScreen()
        searchScreen()
    }
}