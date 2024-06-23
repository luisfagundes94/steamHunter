package com.luisfagundes.steamhunter.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.luisfagundes.designsystem.icon.SteamHunterIcons
import com.luisfagundes.about.R as aboutR
import com.luisfagundes.games.R as gamesR
import com.luisfagundes.profile.R as profileR

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    GAMES(
        selectedIcon = SteamHunterIcons.Games,
        iconTextId = gamesR.string.feature_games_title,
        titleTextId = gamesR.string.feature_games_title,
    ),
    PROFILE(
        selectedIcon = SteamHunterIcons.Profile,
        iconTextId = profileR.string.feature_profile_title,
        titleTextId = profileR.string.feature_profile_title,
    ),
    ABOUT(
        selectedIcon = SteamHunterIcons.About,
        iconTextId = aboutR.string.feature_about_title,
        titleTextId = aboutR.string.feature_about_title,
    ),
}
