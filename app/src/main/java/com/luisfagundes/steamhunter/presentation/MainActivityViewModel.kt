package com.luisfagundes.steamhunter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisfagundes.domain.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import com.luisfagundes.steamhunter.presentation.MainActivityUiState.Loading
import com.luisfagundes.steamhunter.presentation.MainActivityUiState.Success
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : ViewModel() {
    val uiState: StateFlow<MainActivityUiState> = userDataRepository.userData.map {
        Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )

    fun setSteamId(steamId: String) = viewModelScope.launch {
        userDataRepository.setUserSteamId(steamId)
    }
}