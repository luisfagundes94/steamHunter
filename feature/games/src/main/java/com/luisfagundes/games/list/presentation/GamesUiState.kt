package com.luisfagundes.games.list.presentation

import com.luisfagundes.model.Game

sealed interface GamesUiState {
    data object Loading : GamesUiState
    data object Error : GamesUiState
    data class Success(val games: List<com.luisfagundes.model.Game>) : GamesUiState
}
