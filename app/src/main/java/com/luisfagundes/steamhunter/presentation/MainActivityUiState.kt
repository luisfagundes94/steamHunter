package com.luisfagundes.steamhunter.presentation

import com.luisfagundes.domain.model.UserData

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val userData: UserData) : MainActivityUiState
}