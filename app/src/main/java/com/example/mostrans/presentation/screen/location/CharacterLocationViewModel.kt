package com.example.mostrans.presentation.screen.location

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mostrans.firebase.data.repository.CharacterResponse
import com.example.mostrans.firebase.data.repository.Response
import com.example.mostrans.firebase.domain.usecase.UseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterLocationViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    var characterResponse by mutableStateOf<CharacterResponse>(Response.Loading)
        private set

    init {
        getCharacter()
    }

    private fun getCharacter()  = viewModelScope.launch {
        useCases.getLocationCharacter().collect { response ->
            characterResponse = response
        }
    }
}