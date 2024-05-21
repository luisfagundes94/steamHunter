package com.luisfagundes.games.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisfagundes.Dispatcher
import com.luisfagundes.SteamHunterDispatchers.IO
import com.luisfagundes.domain.usecase.GetRecentlyPlayedGames
import com.luisfagundes.result.Result.Error
import com.luisfagundes.result.Result.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val getRecentlyPlayedGames: GetRecentlyPlayedGames,
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow<GamesUiState>(GamesUiState.Loading)
    val uiState: StateFlow<GamesUiState> = _uiState.asStateFlow()

    fun getGames() = viewModelScope.launch(dispatcher) {
        _uiState.value = GamesUiState.Loading
        _uiState.value = when (val result = getRecentlyPlayedGames()) {
            is Success -> GamesUiState.Success(result.data)
            is Error -> GamesUiState.Error
        }
    }
}