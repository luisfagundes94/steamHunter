package com.luisfagundes.games.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.designsystem.component.GameCard

@Composable
fun GamesRoute(
    onGameClick: (String) -> Unit,
    viewModel: GamesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GamesScreen(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        onGameClick = onGameClick
    )
}

@Composable
internal fun GamesScreen(
    modifier: Modifier = Modifier,
    uiState: GamesUiState,
    onGameClick: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        when (uiState) {
            is GamesUiState.Success -> LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(uiState.games) { game ->
                    GameCard(
                        name = game.name,
                        achievementsUnlocked = game.achievementsUnlocked,
                        achievementsTotal = game.achievementsTotal,
                        imageUrl = game.imageUrl
                    )
                }
            }
            is GamesUiState.Error -> Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Deu pau"
            )
            is GamesUiState.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}