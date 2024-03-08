package com.example.mostrans.graphql.domain.usecase

import com.example.mostrans.graphql.data.repository.CharacterRepository
import com.example.mostrans.graphql.domain.model.DetailedCharacter

class GetCharacterUseCase(
    private val characterRepository: CharacterRepository
) {
    suspend fun execute(id: String): DetailedCharacter? {
        return characterRepository.getCharacter(id)
    }
}