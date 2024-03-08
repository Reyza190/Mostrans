package com.example.mostrans.graphql.domain.usecase

import com.example.mostrans.graphql.data.repository.CharacterRepository
import com.example.mostrans.graphql.domain.model.SimpleCharacter

class GetCharactersUseCase(
    private val characterRepository: CharacterRepository
) {
    suspend fun execute(): List<SimpleCharacter> {
        return characterRepository.getCharacters()
    }
}