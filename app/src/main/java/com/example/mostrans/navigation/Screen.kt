package com.example.mostrans.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.example.mostrans.R


sealed class NavigationBarScreen(
    val route: String,
    @StringRes val nameResourceId: Int
) {
    object ListCharacter :
        NavigationBarScreen(route = "list_screen", nameResourceId = R.string.top_bar)

    object Location :
            NavigationBarScreen(route = "location_screen", nameResourceId = R.string.location)
}

sealed class Screen(val route: String) {

    object DetailCharacter : Screen("detail_character_screen")

    object NavigationBar : Screen("navigation_bar_screen")
}