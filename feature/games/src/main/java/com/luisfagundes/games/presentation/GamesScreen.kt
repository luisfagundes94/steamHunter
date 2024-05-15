package com.luisfagundes.games.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.luisfagundes.designsystem.component.GameCard
import com.luisfagundes.domain.model.OwnedGame

@Composable
fun GamesRoute(
    onGameClick: (String) -> Unit
) {
    GamesScreen(
        modifier = Modifier.fillMaxWidth(),
        onGameClick = onGameClick
    )
}

@Composable
internal fun GamesScreen(
    modifier: Modifier = Modifier,
    onGameClick: (String) -> Unit
) {
    val games = listOf(
        OwnedGame(
            appId = 1,
            name = "Fallout: New Vegas",
            achievementsUnlocked = 70,
            achievementsTotal = 75,
            imageUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdn.wallpapersafari.com%2F86%2F8%2FnbTiks.jpg&f=1&nofb=1&ipt=df1be341d086f45885a654e2a9d8364ce711d75288da618ab0b70c5fded974a6&ipo=images"
        ),
        OwnedGame(
            appId = 1,
            name = "Fallout: New Vegas",
            achievementsUnlocked = 70,
            achievementsTotal = 75,
            imageUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdn.wallpapersafari.com%2F86%2F8%2FnbTiks.jpg&f=1&nofb=1&ipt=df1be341d086f45885a654e2a9d8364ce711d75288da618ab0b70c5fded974a6&ipo=images"
        ),
        OwnedGame(
            appId = 1,
            name = "Fallout: New Vegas",
            achievementsUnlocked = 70,
            achievementsTotal = 75,
            imageUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdn.wallpapersafari.com%2F86%2F8%2FnbTiks.jpg&f=1&nofb=1&ipt=df1be341d086f45885a654e2a9d8364ce711d75288da618ab0b70c5fded974a6&ipo=images"
        ),

    )
    LazyColumn(
        modifier = modifier
    ) {
        items(games) { game ->
            GameCard(
                gameName = game.name,
                achievementsUnlocked = game.achievementsUnlocked,
                achievementsTotal = game.achievementsTotal,
                imageUrl = game.imageUrl
            )
        }
    }
}