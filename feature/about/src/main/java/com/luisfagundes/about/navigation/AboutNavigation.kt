package com.luisfagundes.about.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val ABOUT_ROUTE = "about_route/"

fun NavController.navigateToAbout(navOptions: NavOptions) = navigate(ABOUT_ROUTE, navOptions)

fun NavGraphBuilder.aboutScreen() {
    composable(
        route = ABOUT_ROUTE,
    ) {
        // AboutScreen()
    }
}