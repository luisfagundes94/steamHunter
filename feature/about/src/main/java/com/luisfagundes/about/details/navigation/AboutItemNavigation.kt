package com.luisfagundes.about.details.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.luisfagundes.about.details.presentation.AboutItemRoute
import kotlinx.serialization.Serializable

@Serializable
data class AboutItemNavigation(
    val titleResId: Int,
    val descriptionResId: Int,
)

fun NavGraphBuilder.aboutItemScreen(
    onBackClick: () -> Unit
) {
    composable<AboutItemNavigation> {
        val args = it.toRoute<AboutItemNavigation>()
        AboutItemRoute(
            onBackClick = onBackClick,
            titleResId = args.titleResId,
            descriptionResId = args.descriptionResId
        )
    }
}
