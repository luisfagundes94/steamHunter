package com.luisfagundes.about.details.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.luisfagundes.about.R
import com.luisfagundes.designsystem.component.SteamHunterTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutItemRoute(
    onBackClick: () -> Unit,
    descriptionResId: Int,
) {
    SteamHunterTopAppBar(
        title = stringResource(R.string.feature_about_details),
        navigationIcon = Icons.AutoMirrored.Default.ArrowBack,
        navigationIconContentDescription = stringResource(R.string.feature_about_details),
        onNavigationClick = onBackClick
    ) {
        AboutItemDetailsScreen(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            description = LocalContext.current.getString(descriptionResId)
        )
    }
}

@Composable
internal fun AboutItemDetailsScreen(
    modifier: Modifier = Modifier,
    description: String
) {
    Text(
        modifier = modifier,
        text = description
    )
}