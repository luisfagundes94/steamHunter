package com.luisfagundes.games.list.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.designsystem.TryAgain
import com.luisfagundes.designsystem.component.GameCard
import com.luisfagundes.designsystem.component.SteamHunterTopAppBar
import com.luisfagundes.games.R

@Composable
fun GamesRoute(
    onGameClick: (String) -> Unit,
    viewModel: GamesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GamesScreen(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        onGameClick = onGameClick,
        onUpdateGames =  { viewModel.getGames() }
    )
}

@Composable
internal fun GamesScreen(
    modifier: Modifier = Modifier,
    uiState: GamesUiState,
    onGameClick: (String) -> Unit,
    onUpdateGames: () -> Unit = {}
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
                items(uiState.games) { game ->
                    GameCard(
                        modifier = Modifier
                            .clickable { onGameClick(game.appId.toString()) }
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 4.dp),
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
            is GamesUiState.Loading -> CircularProgressIndicator()
        }
    }
}
