package com.luisfagundes.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.luisfagundes.designsystem.theme.SteamHunterTheme
import com.luisfagundes.designsystem.theme.ThemePreviews
import com.luisfagundes.designsystem.theme.spacing
import com.luisfagundes.designsystem.utils.calculatePercentage

@Composable
fun GameCard(
    modifier: Modifier = Modifier,
    name: String,
    achievementsUnlocked: Int,
    achievementsTotal: Int,
    imageUrl: String
) {
    val completionPercentage = calculatePercentage(
        total = achievementsTotal,
        unlocked = achievementsUnlocked
    )
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(MaterialTheme.spacing.default)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(MaterialTheme.spacing.verySmall)
                    .clip(RoundedCornerShape(MaterialTheme.spacing.small))
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.default))
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
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.verySmall))
                Text(
                    text = "$achievementsUnlocked/$achievementsTotal",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.verySmall))
                LinearProgressIndicator(
                    progress = { completionPercentage / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.spacing.verySmall),
                )
            }
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.default))
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "$completionPercentage%",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun GameCardPreview() {
    SteamHunterTheme {
        GameCard(
            name = "Fallout: New Vegas",
            achievementsUnlocked = 70,
            achievementsTotal = 75,
            imageUrl = ""
        )
    }
}
