package com.luisfagundes.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val SEARCH_ROUTE = "search_route/"

fun NavController.navigateToSearch() = navigate(SEARCH_ROUTE)

fun NavGraphBuilder.searchScreen() {
    composable(
        route = SEARCH_ROUTE,
    ) {
        // SearchScreen()
    }
}