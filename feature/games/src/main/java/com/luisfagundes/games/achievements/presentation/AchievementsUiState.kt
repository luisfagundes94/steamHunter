package com.luisfagundes.games.achievements.presentation

import com.luisfagundes.model.Achievement

sealed interface AchievementsUiState {
    data object Loading : AchievementsUiState
    data object Empty : AchievementsUiState
    data class Success(val achievements: List<com.luisfagundes.model.Achievement> = emptyList()) : AchievementsUiState
    data object Error : AchievementsUiState

}