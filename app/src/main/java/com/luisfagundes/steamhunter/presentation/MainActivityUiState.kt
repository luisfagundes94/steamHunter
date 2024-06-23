package com.luisfagundes.steamhunter.presentation

import com.luisfagundes.model.UserData

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val userData: com.luisfagundes.model.UserData) : MainActivityUiState
}
