package com.luisfagundes.games.achievements.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.luisfagundes.games.achievements.presentation.AchievementsRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

const val ACHIEVEMENTS_ROUTE = "achievements"
private const val GAME_ID = "gameId"
private val URL_CHARACTER_ENCODING = UTF_8.name()

internal class Args(val gameId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        URLDecoder.decode(checkNotNull(savedStateHandle[GAME_ID]), URL_CHARACTER_ENCODING)
    )
}

fun NavController.navigateToAchievements(
    gameId: String,
    navOptions: NavOptionsBuilder.() -> Unit = {}
) = navigate(createArgRoute(gameId), navOptions)

fun createArgRoute(gameId: String): String {
    val encodedId = URLEncoder.encode(gameId, URL_CHARACTER_ENCODING)
    return "$ACHIEVEMENTS_ROUTE/$encodedId"
}

fun NavGraphBuilder.achievementsScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "$ACHIEVEMENTS_ROUTE/{$GAME_ID}",
        arguments = listOf(
            navArgument(GAME_ID) { type = NavType.StringType }
        )
    ) {
        AchievementsRoute(
            onBackClick = onBackClick
        )
    }
}