package com.luisfagundes.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.luisfagundes.designsystem.R
import com.luisfagundes.designsystem.theme.SteamHunterTheme
import com.luisfagundes.designsystem.theme.ThemePreviews

@Composable
fun AchievementCard(
    modifier: Modifier = Modifier,
    name: String,
    unlockedIconUrl: String,
    lockedIconUrl: String,
    achieved: Boolean,
    description: String,
    onAchievementClick: () -> Unit = { },
) {
    val hiddenAchievementText = stringResource(
        id = R.string.core_designsystem_hidden_achievement
    )
    val borderColor = if (achieved) MaterialTheme.colorScheme.primary else null

    Card(
        shape = RoundedCornerShape(12.dp),
        border = borderColor?.let { BorderStroke(1.dp, it) },
        modifier = modifier.clickable { onAchievementClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = if (achieved) unlockedIconUrl else lockedIconUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description.ifBlank { hiddenAchievementText },
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun AchievementCardPreview() {
    SteamHunterTheme {
        AchievementCard(
            name = "Swamp Tourist",
            unlockedIconUrl = "",
            lockedIconUrl = "",
            description = "Unlocks the Swamp Tourist achievement",
            achieved = true
        )
    }
}