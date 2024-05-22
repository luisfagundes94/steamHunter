package com.luisfagundes.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.luisfagundes.designsystem.R
import com.luisfagundes.designsystem.icon.SteamHunterIcons
import com.luisfagundes.designsystem.theme.SteamHunterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SteamHunterTopAppBar(
    title: String,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = Color.Transparent
    ),
    navigationIcon: ImageVector = Icons.AutoMirrored.Default.ArrowBack,
    navigationIconContentDescription: String? = null,
    onNavigationClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Column {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = navigationIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            },
            colors = colors
        )
        content.invoke()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SteamHunterTopAppBar(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    content: @Composable () -> Unit = {},
) {
    Column {
        TopAppBar(
            title = { Text(text = stringResource(id = titleRes)) },
            colors = colors,
            modifier = modifier.testTag("simpleSteamHunterTopAppBar"),
        )
        content.invoke()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SteamHunterTopAppBar(
    @StringRes titleRes: Int,
    navigationIcon: ImageVector,
    navigationIconContentDescription: String,
    actionIcon: ImageVector,
    actionIconContentDescription: String,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
) {
    TopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = navigationIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        actions = {
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = actionIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        colors = colors,
        modifier = modifier.testTag("steamHunterTopAppBar"),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Top App Bar")
@Composable
private fun SteamHunterTopAppBarPreview() {
    SteamHunterTheme {
        SteamHunterTopAppBar(
            titleRes = android.R.string.untitled,
            navigationIcon = SteamHunterIcons.Search,
            navigationIconContentDescription = "Navigation icon",
            actionIcon = SteamHunterIcons.MoreVert,
            actionIconContentDescription = "Action icon",
        )
    }
}