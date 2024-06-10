package usecases

import com.luisfagundes.domain.repository.SteamRepository
import com.luisfagundes.domain.repository.UserDataRepository
import com.luisfagundes.domain.usecase.GetRecentlyPlayedGames
import com.luisfagundes.result.Result
import com.luisfagundes.testing.model.achievements
import com.luisfagundes.testing.model.games
import com.luisfagundes.testing.model.playerAchievement
import com.luisfagundes.testing.model.userData
import com.luisfagundes.testing.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetRecentlyPlayedGamesTest {

    @get:Rule
    val dispatcher = MainDispatcherRule()

    private lateinit var getRecentlyPlayedGames: GetRecentlyPlayedGames
    private val steamRepository: SteamRepository = mockk()
    private val userDataRepository: UserDataRepository = mockk()

    @Before
    fun setUp() {
        getRecentlyPlayedGames = GetRecentlyPlayedGames(
            steamRepository = steamRepository,
            userDataRepository = userDataRepository,
            dispatcher = UnconfinedTestDispatcher()
        )
    }

    @Test
    fun `invoke returns list of games with achievements when successful`() = runTest {
        val game = games.first()
        val steamId = userData.steamId

        coEvery { userDataRepository.userData.first() } returns userData
        coEvery { steamRepository.getRecentlyPlayedGames(steamId) } returns Result.Success(games)
        coEvery {
            steamRepository.getPlayerAchievements(steamId, game.appId)
        } returns Result.Success(playerAchievement)

        val result = getRecentlyPlayedGames.invoke()

        assertEquals(result, Result.Success(games))
        assertTrue((result as Result.Success).data.isNotEmpty())
        assertEquals(result.data.first().achievementsUnlocked, achievements.count { it.achieved })
        assertEquals(result.data.first().achievementsTotal, achievements.size)

        coVerify(exactly = 1) { userDataRepository.userData.first() }
        coVerify(exactly = 1) { steamRepository.getRecentlyPlayedGames(steamId) }
        coVerify(exactly = 1) { steamRepository.getPlayerAchievements(steamId, game.appId) }
    }

    @Test
    fun `invoke returns error when exception occurs`() = runTest {
        val exception = Exception("User data error")
        coEvery { userDataRepository.userData.first() } throws exception

        val result = getRecentlyPlayedGames.invoke()

        assertEquals(result, Result.Error(exception))
        coVerify(exactly = 1) { userDataRepository.userData.first() }
        coVerify(exactly = 0) { steamRepository.getRecentlyPlayedGames(any()) }
    }
}
