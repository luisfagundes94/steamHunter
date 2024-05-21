package com.luisfagundes.games.presentation

import com.luisfagundes.domain.model.Game

sealed interface GamesUiState {
    data object Loading : GamesUiState
    data object Error : GamesUiState
    data class Success(val games: List<Game>) : GamesUiState

}