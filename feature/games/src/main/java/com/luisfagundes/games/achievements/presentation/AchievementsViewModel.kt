package com.luisfagundes.games.achievements.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.luisfagundes.Dispatcher
import com.luisfagundes.SteamHunterDispatchers.IO
import com.luisfagundes.domain.usecase.GetGameAchievements
import com.luisfagundes.games.achievements.navigation.AchievementsNavigation
import com.luisfagundes.games.achievements.presentation.AchievementsUiState.Error
import com.luisfagundes.games.achievements.presentation.AchievementsUiState.Loading
import com.luisfagundes.games.achievements.presentation.AchievementsUiState.Success
import com.luisfagundes.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getGameAchievements: GetGameAchievements,
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow<AchievementsUiState>(Loading)
    val uiState = _uiState.asStateFlow()

    private val args = savedStateHandle.toRoute<AchievementsNavigation>()

    fun getAchievements() = viewModelScope.launch(dispatcher) {
        _uiState.value = Loading
        val gameId = args.gameId
        _uiState.value = when (val result = getGameAchievements(gameId)) {
            is Result.Success -> Success(result.data)
            is Result.Error -> Error
        }
    }
}
