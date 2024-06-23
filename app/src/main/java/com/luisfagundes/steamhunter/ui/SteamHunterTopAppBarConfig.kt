package com.luisfagundes.steamhunter.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.luisfagundes.designsystem.component.SteamHunterTopAppBar
import com.luisfagundes.designsystem.icon.SteamHunterIcons
import com.luisfagundes.steamhunter.R
import com.luisfagundes.steamhunter.navigation.TopLevelDestination

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun SteamHunterTopAppBarConfig(
    destination: TopLevelDestination,
    onTopAppBarActionClick: () -> Unit,
    appState: SteamHunterAppState
) {
    when (destination) {
        TopLevelDestination.GAMES -> SteamHunterTopAppBar(
            titleRes = destination.titleTextId,
            navigationIcon = SteamHunterIcons.Search,
            navigationIconContentDescription = stringResource(
                id = R.string.feature_settings_top_app_bar_navigation_icon_description,
            ),
            actionIcon = SteamHunterIcons.Sort,
            actionIconContentDescription = stringResource(R.string.sort_list),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
            ),
            onActionClick = { onTopAppBarActionClick() },
            onNavigationClick = { appState.navigateToSearch() },
        )

        else -> SteamHunterTopAppBar(
            titleRes = destination.titleTextId,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
            ),
        )
    }
}
