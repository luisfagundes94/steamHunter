package com.luisfagundes.steamhunter.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.luisfagundes.about.list.navigation.AboutNavigation
import com.luisfagundes.about.list.navigation.navigateToAbout
import com.luisfagundes.data.utils.NetworkMonitor
import com.luisfagundes.games.list.navigation.GamesNavigation
import com.luisfagundes.games.list.navigation.navigateToGames
import com.luisfagundes.profile.navigation.ProfileNavigation
import com.luisfagundes.profile.navigation.navigateToProfile
import com.luisfagundes.search.navigation.navigateToSearch
import com.luisfagundes.steamhunter.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberSteamHunterAppState(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): SteamHunterAppState {
    return remember(
        navController,
        coroutineScope,
        windowSizeClass,
        networkMonitor,
    ) {
        SteamHunterAppState(
            navController = navController,
            coroutineScope = coroutineScope,
            windowSizeClass = windowSizeClass,
            networkMonitor = networkMonitor,
        )
    }
}

@Stable
class SteamHunterAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            GamesNavigation::class.java.name -> TopLevelDestination.GAMES
            ProfileNavigation::class.java.name -> TopLevelDestination.PROFILE
            AboutNavigation::class.java.name -> TopLevelDestination.ABOUT
            else -> null
        }

    val shouldShowBottomBar: Boolean
        @Composable get() = currentTopLevelDestination != null

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.GAMES -> navController.navigateToGames(topLevelNavOptions)
            TopLevelDestination.PROFILE -> navController.navigateToProfile(topLevelNavOptions)
            TopLevelDestination.ABOUT -> navController.navigateToAbout(topLevelNavOptions)
        }
    }

    fun navigateToSearch() = navController.navigateToSearch()
}
