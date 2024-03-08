package com.example.mostrans

import com.example.mostrans.navigation.NavigationBarScreen

data class MainActivityUiState(
    val startScreen: NavigationBarScreen = NavigationBarScreen.ListCharacter,
    val isLoading: Boolean = false
)