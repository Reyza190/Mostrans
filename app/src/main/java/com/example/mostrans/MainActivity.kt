package com.example.mostrans

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.mostrans.navigation.AppNavHost
import com.example.mostrans.presentation.screen.list.CharactersViewModel
import com.example.mostrans.presentation.screen.list.ListCharactersScreen
import com.example.mostrans.ui.theme.MostransTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MostransTheme(navController = navController) {
                // A surface container using the 'background' color from the theme
                val viewModel: MainActivityViewModel = hiltViewModel()
                val uiState by viewModel.state.collectAsState()

                if (!uiState.isLoading) {
                    AppNavHost(
                        navController = navController,
                        navigationBarStartScreen = uiState.startScreen
                    )
                }
            }
        }
    }
}
