package usecases

import com.luisfagundes.domain.repository.SteamRepository
import com.luisfagundes.domain.repository.UserDataRepository
import com.luisfagundes.domain.usecase.GetGameAchievements
import com.luisfagundes.result.Result
import com.luisfagundes.testing.model.STEAM_ID
import com.luisfagundes.testing.model.achievements
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
class GetGameAchievementsTest {

    private val steamRepository: SteamRepository = mockk()
    private val userRepository: UserDataRepository = mockk()
    private lateinit var getGameAchievements: GetGameAchievements

    @Before
    fun setUp() {
        getGameAchievements = GetGameAchievements(steamRepository, userRepository)
    }

    @Test
    fun `invoke should return achievements list when repository call is successful`() = runTest {
        val appId = 67890

        coEvery { userRepository.userData } returns flowOf(userData)
        coEvery { steamRepository.getAchievements(STEAM_ID, appId) } returns Result.Success(achievements)

        val result = getGameAchievements(appId)

        assertEquals(result, Result.Success(achievements))
        coVerify { userRepository.userData }
        coVerify { steamRepository.getAchievements(STEAM_ID, appId) }
    }

    @Test
    fun `invoke should return error when repository call fails`() = runTest {
        val appId = 67890
        val exception = Exception("Error")

        coEvery { userRepository.userData } returns flowOf(userData)
        coEvery { steamRepository.getAchievements(STEAM_ID, appId) } returns Result.Error(exception)

        val result = getGameAchievements(appId)

        assertEquals(result, Result.Error(exception))
        coVerify { userRepository.userData }
        coVerify { steamRepository.getAchievements(STEAM_ID, appId) }
    }

    @Test
    fun `invoke should return error when getting user data fails`() = runTest {
        val appId = 67890
        val exception = Exception("User Data Error")

        coEvery { userRepository.userData } throws exception

        val result = getGameAchievements(appId)

        assertEquals(result, Result.Error(exception))
        coVerify { userRepository.userData }
        coVerify(exactly = 0) { steamRepository.getAchievements(any(), any()) }
    }
}
