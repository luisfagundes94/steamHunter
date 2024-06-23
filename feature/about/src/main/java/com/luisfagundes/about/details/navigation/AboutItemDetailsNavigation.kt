package com.luisfagundes.about.details.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.luisfagundes.about.details.presentation.AboutItemRoute
import kotlinx.serialization.Serializable

@Serializable
data class AboutItemRoute(
    val descriptionResId: Int,
)

fun NavGraphBuilder.aboutItemScreen(
    onBackClick: () -> Unit
) {
    composable<AboutItemRoute> {
        val args = it.toRoute<AboutItemRoute>()
        AboutItemRoute(
            onBackClick = onBackClick,
            descriptionResId = args.descriptionResId
        )
    }
}

