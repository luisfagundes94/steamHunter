package com.luisfagundes.games.achievements.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.designsystem.TryAgain
import com.luisfagundes.designsystem.component.AchievementCard
import com.luisfagundes.designsystem.component.SteamHunterTopAppBar
import com.luisfagundes.model.Achievement
import com.luisfagundes.games.R
import com.luisfagundes.utils.doNothing

private const val GOOGLE_SEARCH_URL = "https://www.google.com/search?q="
private const val ACHIEVEMENT = "achievement"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementsRoute(
    onBackClick: () -> Unit,
    viewModel: AchievementsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    SteamHunterTopAppBar(
        title = stringResource(R.string.feature_games_achievements),
        navigationIcon = Icons.AutoMirrored.Default.ArrowBack,
        navigationIconContentDescription = stringResource(R.string.feature_games_achievement_image),
        onNavigationClick = onBackClick
    ) {
        AchievementsScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            onUpdateAchievements = { viewModel.getAchievements() },
            onAchievementClick = { achievement ->
                val intent = buildSearchOnBrowserIntent(achievement)
                context.startActivity(intent)
            }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.getAchievements()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AchievementsScreen(
    modifier: Modifier = Modifier,
    uiState: AchievementsUiState,
    onUpdateAchievements: () -> Unit,
    onAchievementClick: (String) -> Unit = {},
) {
    PullToRefreshBox(
        isRefreshing = uiState is AchievementsUiState.Loading,
        onRefresh = onUpdateAchievements
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                is AchievementsUiState.Success -> AchievementsList(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    achievements = uiState.achievements,
                    onAchievementClick = onAchievementClick
                )

                is AchievementsUiState.Error -> TryAgain(
                    modifier = Modifier,
                    title = stringResource(R.string.feature_games_default_error_msg),
                    onClick = onUpdateAchievements
                )

                is AchievementsUiState.Empty -> Text(
                    text = stringResource(R.string.feature_games_empty_msg),
                )

                else -> doNothing()
            }
        }
    }
}

@Composable
private fun Title(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 16.dp)
    )
}

@Composable
fun AchievementsList(
    modifier: Modifier = Modifier,
    achievements: List<Achievement>,
    onAchievementClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        val (unlocked, locked) = achievements.partition { achievement ->
            achievement.achieved
        }
        if (locked.isNotEmpty()) {
            item {
                Title(stringResource(id = R.string.feature_games_locked_msg))
            }
            items(locked) { achievement ->
                Achievement(
                    item = achievement,
                    onClick = onAchievementClick
                )
            }
        }
        if (unlocked.isNotEmpty()) {
            item {
                Title(stringResource(id = R.string.feature_games_unlocked_msg))
            }
            items(unlocked) { achievement ->
                Achievement(
                    item = achievement,
                    onClick = onAchievementClick
                )
            }
        }
    }
}

@Composable
private fun Achievement(
    item: Achievement,
    onClick: (String) -> Unit
) {
    AchievementCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        name = item.name,
        unlockedIconUrl = item.unlockedIconUrl,
        lockedIconUrl = item.lockedIconUrl,
        description = item.description,
        achieved = item.achieved,
        onAchievementClick = { onClick(item.name) }
    )
}

private fun buildSearchOnBrowserIntent(achievement: String): Intent {
    val queryUrl = "$GOOGLE_SEARCH_URL${Uri.encode(achievement)}+$ACHIEVEMENT"
    return Intent(Intent.ACTION_VIEW, Uri.parse(queryUrl))
}