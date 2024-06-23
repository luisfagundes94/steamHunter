package com.luisfagundes.about.details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.luisfagundes.about.R
import com.luisfagundes.designsystem.component.SteamHunterTopAppBar
import com.luisfagundes.designsystem.theme.spacing
import dev.jeziellago.compose.markdowntext.MarkdownText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutItemRoute(
    onBackClick: () -> Unit,
    titleResId: Int,
    descriptionResId: Int,
) {
    SteamHunterTopAppBar(
        title = stringResource(R.string.feature_about_details),
        navigationIcon = Icons.AutoMirrored.Default.ArrowBack,
        navigationIconContentDescription = stringResource(R.string.feature_about_details),
        onNavigationClick = onBackClick
    ) {
        AboutItemScreen(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.default),
            title = LocalContext.current.getString(titleResId),
            description = LocalContext.current.getString(descriptionResId)
        )
    }
}

@Composable
internal fun AboutItemScreen(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(MaterialTheme.spacing.small))
        MarkdownText(
            markdown = description,
            linkColor = MaterialTheme.colorScheme.primary
        )
    }
}
