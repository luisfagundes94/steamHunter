package com.luisfagundes.games.list.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.designsystem.TryAgain
import com.luisfagundes.designsystem.component.GameCard
import com.luisfagundes.designsystem.theme.spacing
import com.luisfagundes.games.R
import com.luisfagundes.utils.doNothing

@Composable
fun GamesRoute(
    onGameClick: (Int) -> Unit,
    viewModel: GamesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getGames()
    }

    GamesScreen(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        onGameClick = onGameClick,
        onUpdateGames = { viewModel.getGames(forceRefresh = true) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GamesScreen(
    modifier: Modifier = Modifier,
    uiState: GamesUiState,
    onGameClick: (Int) -> Unit,
    onUpdateGames: () -> Unit = {}
) {
    PullToRefreshBox(
        isRefreshing = uiState is GamesUiState.Loading,
        onRefresh = onUpdateGames
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                is GamesUiState.Success -> LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.TopStart)
                ) {
                    item {
                        Text(
                            modifier = Modifier.padding(MaterialTheme.spacing.default),
                            text = stringResource(R.string.feature_games_recently_played),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    items(uiState.games) { game ->
                        GameCard(
                            modifier = Modifier
                                .clickable { onGameClick(game.appId) }
                                .fillMaxWidth()
                                .padding(horizontal = MaterialTheme.spacing.default)
                                .padding(vertical = MaterialTheme.spacing.small),
                            name = game.name,
                            achievementsUnlocked = game.achievementsUnlocked,
                            achievementsTotal = game.achievementsTotal,
                            imageUrl = game.imageUrl
                        )
                    }
                }

                is GamesUiState.Error -> TryAgain(
                    title = stringResource(R.string.feature_games_default_error_msg),
                    onClick = onUpdateGames
                )

                else -> doNothing()
            }
        }
    }
}
