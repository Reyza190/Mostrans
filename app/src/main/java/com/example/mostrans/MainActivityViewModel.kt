package com.example.mostrans

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mostrans.navigation.NavigationBarScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivityViewModel() : ViewModel() {
    private val _state = MutableStateFlow(MainActivityUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    startScreen = NavigationBarScreen.ListCharacter,
                    isLoading =false
                )
            }
        }
    }
}