package com.luisfagundes.games.viewmodel

import app.cash.turbine.test
import com.luisfagundes.domain.usecase.GetRecentlyPlayedGames
import com.luisfagundes.games.list.presentation.GamesUiState
import com.luisfagundes.games.list.presentation.GamesViewModel
import com.luisfagundes.result.Result
import com.luisfagundes.testing.model.games
import com.luisfagundes.testing.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GamesViewModelTest {

    @get:Rule
    val dispatcher = MainDispatcherRule()

    private val getRecentlyPlayedGames: GetRecentlyPlayedGames = mockk()
    private lateinit var viewModel: GamesViewModel

    @Before
    fun setup() {
        viewModel = GamesViewModel(getRecentlyPlayedGames)
    }

    @Test
    fun `should not call getGames when forceRefresh is false and games were already loaded`() =
        runTest {
            GamesViewModel(getRecentlyPlayedGames, GamesUiState.Success(emptyList())).apply {
                getGames(forceRefresh = false)
            }

            coVerify(exactly = 0) { getRecentlyPlayedGames.invoke() }
        }

    @Test
    fun `should call getGames when forceRefresh is true and games were not loaded`() = runTest {
        coEvery { getRecentlyPlayedGames.invoke() } returns mockk()

        GamesViewModel(getRecentlyPlayedGames, GamesUiState.Loading).apply {
            getGames(forceRefresh = true)
        }

        coVerify(exactly = 1) { getRecentlyPlayedGames.invoke() }
    }

    @Test
    fun `state should be loading by default`() = runTest {
        assertEquals(
            GamesUiState.Loading,
            viewModel.uiState.value
        )
    }

    @Test
    fun `state should be success when fetching games`() = runTest {
        coEvery { getRecentlyPlayedGames.invoke() } returns Result.Success(games)

        viewModel.uiState.test {
            assertTrue(awaitItem() is GamesUiState.Loading)
            viewModel.getGames(forceRefresh = true)
            assertTrue(awaitItem() is GamesUiState.Success)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `state should be error when an exception is thrown`() = runTest {
        coEvery { getRecentlyPlayedGames() } returns Result.Error(Exception())

        viewModel.uiState.test {
            assertTrue(awaitItem() is GamesUiState.Loading)
            viewModel.getGames(forceRefresh = true)
            assertTrue(awaitItem() is GamesUiState.Error)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
