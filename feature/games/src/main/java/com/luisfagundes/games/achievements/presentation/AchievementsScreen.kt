package com.luisfagundes.games.achievements.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.designsystem.TryAgain
import com.luisfagundes.designsystem.component.AchievementCard
import com.luisfagundes.designsystem.component.SteamHunterTopAppBar
import com.luisfagundes.games.R

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
            onTryAgainClick = { viewModel.getAchievements() },
            onAchievementClick = { achievement ->
                val queryUrl = "$GOOGLE_SEARCH_URL${Uri.encode(achievement)}+$ACHIEVEMENT"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(queryUrl))
                context.startActivity(intent)
            }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.getAchievements()
    }
}

@Composable
internal fun AchievementsScreen(
    modifier: Modifier = Modifier,
    uiState: AchievementsUiState,
    onTryAgainClick: () -> Unit,
    onAchievementClick: (String) -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        when (uiState) {
            is AchievementsUiState.Success -> LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                val sortedItems = uiState.achievements.sortedBy { it.achieved }
                items(sortedItems) { achievement ->
                    AchievementCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 4.dp),
                        name = achievement.name,
                        unlockedIconUrl = achievement.unlockedIconUrl,
                        lockedIconUrl = achievement.lockedIconUrl,
                        description = achievement.description,
                        achieved = achievement.achieved,
                        onAchievementClick = { onAchievementClick(achievement.name) }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            is AchievementsUiState.Error -> TryAgain(
                modifier = Modifier,
                title = stringResource(R.string.feature_games_default_error_msg),
                onClick = onTryAgainClick
            )

            is AchievementsUiState.Empty -> Text(
                text = stringResource(R.string.feature_games_empty_msg),
            )

            is AchievementsUiState.Loading -> CircularProgressIndicator()
        }
    }
}