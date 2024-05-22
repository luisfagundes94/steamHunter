package com.luisfagundes.games.achievements.presentation

import com.luisfagundes.domain.model.Achievement

sealed interface AchievementsUiState {
    data object Loading : AchievementsUiState
    data object Empty : AchievementsUiState
    data class Success(val achievements: List<Achievement> = emptyList()) : AchievementsUiState
    data object Error : AchievementsUiState

}