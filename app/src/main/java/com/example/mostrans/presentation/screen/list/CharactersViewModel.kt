package com.example.mostrans.presentation.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mostrans.graphql.domain.model.DetailedCharacter
import com.example.mostrans.graphql.domain.usecase.GetCharactersUseCase
import com.example.mostrans.graphql.domain.model.SimpleCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(CharactersState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }
            _state.update { it.copy(
                characters = getCharactersUseCase.execute(),
                isLoading = false
            ) }
        }
    }


    data class CharactersState(
        val characters: List<SimpleCharacter> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCharacter: DetailedCharacter? = null
    )

}