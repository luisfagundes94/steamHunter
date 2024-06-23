package usecases

import com.luisfagundes.domain.repository.SteamRepository
import com.luisfagundes.domain.repository.UserDataRepository
import com.luisfagundes.domain.usecase.GetRecentlyPlayedGames
import com.luisfagundes.result.Result
import com.luisfagundes.testing.model.STEAM_ID
import com.luisfagundes.testing.model.games
import com.luisfagundes.testing.model.userData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GetRecentlyPlayedGamesTest {

    private val steamRepository: SteamRepository = mockk()
    private val userDataRepository: UserDataRepository = mockk()
    private lateinit var getRecentlyPlayedGames: GetRecentlyPlayedGames

    @Before
    fun setUp() {
        getRecentlyPlayedGames = GetRecentlyPlayedGames(steamRepository, userDataRepository)
    }

    @Test
    fun `invoke should return games with achievements list when repository call is successful`() =
        runTest {
            coEvery { userDataRepository.userData } returns flowOf(userData)
            coEvery {
                steamRepository.getGamesWithAchievements(STEAM_ID)
            } returns Result.Success(games)

            val result = getRecentlyPlayedGames()

            assertEquals(result, Result.Success(games))
            coVerify { userDataRepository.userData }
            coVerify { steamRepository.getGamesWithAchievements(STEAM_ID) }
        }

    @Test
    fun `invoke should return error when repository call fails`() = runTest {
        val exception = Exception("Error")

        coEvery { userDataRepository.userData } returns flowOf(userData)
        coEvery { steamRepository.getGamesWithAchievements(STEAM_ID) } returns Result.Error(exception)

        val result = getRecentlyPlayedGames()

        assertEquals(result, Result.Error(exception))
        coVerify { userDataRepository.userData }
        coVerify { steamRepository.getGamesWithAchievements(STEAM_ID) }
    }

    @Test
    fun `invoke should return error when getting user data fails`() = runTest {
        val exception = Exception("User Data Error")

        coEvery { userDataRepository.userData } throws exception

        val result = getRecentlyPlayedGames()

        assertEquals(result, Result.Error(exception))
        coVerify { userDataRepository.userData }
        coVerify(exactly = 0) { steamRepository.getGamesWithAchievements(any()) }
    }
}
