package com.luisfagundes.about.list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.luisfagundes.about.list.presentation.AboutRoute
import kotlinx.serialization.Serializable

@Serializable
object AboutNavigation

fun NavController.navigateToAbout(navOptions: NavOptions) = navigate(AboutNavigation, navOptions)

fun NavGraphBuilder.aboutScreen(
    onItemClick: (descriptionId: Int) -> Unit
) {
    composable<AboutNavigation> {
        AboutRoute(
            onItemClick = onItemClick
        )
    }
}
