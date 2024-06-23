package com.luisfagundes.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object ProfileNavigation

fun NavController.navigateToProfile(navOptions: NavOptions) = navigate(ProfileNavigation, navOptions)

fun NavGraphBuilder.profileScreen() {
    composable<ProfileNavigation>{
        // ProfileScreen()
    }
}