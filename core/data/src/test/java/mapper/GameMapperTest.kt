package mapper

import com.luisfagundes.data.mapper.GameMapperImpl
import com.luisfagundes.domain.mapper.GameMapper
import com.luisfagundes.model.GameResponse
import com.luisfagundes.model.RecentlyPlayedGamesBodyResponse
import com.luisfagundes.model.RecentlyPlayedGamesResponse
import com.luisfagundes.testing.model.achievements
import com.luisfagundes.testing.model.games
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

private const val IMAGE_BASE_URL = "https://media.steampowered.com/steamcommunity/public/images/apps/"

class GameMapperTest {

    private lateinit var mapper: GameMapper

    @Before
    fun setUp() {
        mapper = GameMapperImpl
    }

    @Test
    fun `mapToDomain should correctly map RecentlyPlayedGamesBodyResponse to List of Game`() {
        val gameResponse = GameResponse(appId = 123, name = "Test Game", iconHash = "icon123")
        val recentlyPlayedGamesBodyResponse = RecentlyPlayedGamesBodyResponse(
            response = RecentlyPlayedGamesResponse(
                totalCount = 10,
                games = listOf(gameResponse)
            )
        )

        val games = GameMapperImpl.run { recentlyPlayedGamesBodyResponse.mapToDomain() }

        assertEquals(1, games.size)
        val game = games.first()
        assertEquals(123, game.appId)
        assertEquals("Test Game", game.name)
        assertEquals(0, game.achievementsUnlocked)
        assertEquals(0, game.achievementsTotal)
        assertEquals("${IMAGE_BASE_URL}123/icon123.jpg", game.imageUrl)
    }

    @Test
    fun `mergeWith should correctly merge Game with Achievements`() {
        val game = games.first()

        val mergedGame = GameMapperImpl.run { game.mergeWith(achievements) }

        assertEquals(0, mergedGame.appId)
        assertEquals("Hunt: Showdown", mergedGame.name)
        assertEquals(1, mergedGame.achievementsUnlocked) // 1 out of 2 achievements unlocked
        assertEquals(2, mergedGame.achievementsTotal) // Total achievements
        assertEquals("", mergedGame.imageUrl)
    }

    @Test
    fun `mergeWith should handle null achievements list`() {
        val game = games.first()

        val mergedGame = GameMapperImpl.run { game.mergeWith(null) }

        assertEquals(0, mergedGame.appId)
        assertEquals("Hunt: Showdown", mergedGame.name)
        assertEquals(0, mergedGame.achievementsUnlocked) // No achievements unlocked
        assertEquals(0, mergedGame.achievementsTotal) // No total achievements
        assertEquals("", mergedGame.imageUrl)
    }
}
