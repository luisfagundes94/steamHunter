package com.luisfagundes.games.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisfagundes.Dispatcher
import com.luisfagundes.SteamHunterDispatchers.IO
import com.luisfagundes.domain.usecase.GetGameAchievements
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
    private val getGameAchievements: GetGameAchievements,
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow<GamesUiState>(GamesUiState.Loading)
    val uiState: StateFlow<GamesUiState> = _uiState.asStateFlow()

    init {
        getGames("76561198118228764")
    }

    private fun getGames(steamId: String) = viewModelScope.launch(dispatcher) {
        _uiState.value = GamesUiState.Loading
        val result = getGameAchievements.invoke(steamId)
        _uiState.value = when (result) {
            is Success -> GamesUiState.Success(result.data)
            is Error -> GamesUiState.Error
        }
    }
}