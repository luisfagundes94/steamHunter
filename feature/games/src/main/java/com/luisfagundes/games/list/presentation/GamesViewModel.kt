package com.luisfagundes.games.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisfagundes.domain.usecase.GetRecentlyPlayedGames
import com.luisfagundes.result.Result.Error
import com.luisfagundes.result.Result.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val getRecentlyPlayedGames: GetRecentlyPlayedGames,
    initialState: GamesUiState = GamesUiState.Loading
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<GamesUiState> = _uiState.asStateFlow()

    fun getGames(
        forceRefresh: Boolean = false,
    ) = viewModelScope.launch {
        if (shouldLoad(forceRefresh)) {
            _uiState.value = GamesUiState.Loading
            _uiState.value = when (val result = getRecentlyPlayedGames()) {
                is Success -> GamesUiState.Success(result.data)
                is Error -> GamesUiState.Error
            }
        }
    }

    private fun shouldLoad(
        forceRefresh: Boolean
    ) = forceRefresh || _uiState.value !is GamesUiState.Success
}
