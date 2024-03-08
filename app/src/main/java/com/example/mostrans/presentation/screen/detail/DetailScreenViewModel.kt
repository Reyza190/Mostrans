package com.example.mostrans.presentation.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mostrans.graphql.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.SavedStateHandle
import com.example.mostrans.firebase.data.repository.AddCharacterResponse
import com.example.mostrans.firebase.data.repository.Response
import com.example.mostrans.firebase.domain.model.DetailedCharacterFirebase
import com.example.mostrans.firebase.domain.usecase.UseCases
import com.example.mostrans.graphql.domain.model.DetailedCharacter

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = MutableStateFlow(CharacterState())
    val state = _state.asStateFlow()

    var addCharacterResponse by mutableStateOf<AddCharacterResponse>(Response.Success(false))
        private set


    private val id = savedStateHandle.get<String>("id")

    init {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }
            _state.update { it.copy(
                character = getCharacterUseCase.execute(id = id!!),
                isLoading = false
            ) }
        }
    }

    fun addCharacter(character: DetailedCharacterFirebase) = viewModelScope.launch {
        addCharacterResponse = Response.Loading
        addCharacterResponse = useCases.addLocation(character)
    }

    data class CharacterState(
        val character: DetailedCharacter? = null,
        val isLoading: Boolean = false,
    )
}