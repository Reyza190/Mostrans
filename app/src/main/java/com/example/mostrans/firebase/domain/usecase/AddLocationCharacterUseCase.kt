package com.example.mostrans.firebase.domain.usecase

import com.example.mostrans.firebase.data.repository.LocationRepository
import com.example.mostrans.firebase.domain.model.DetailedCharacterFirebase
import com.example.mostrans.graphql.domain.model.DetailedCharacter

class AddLocationCharacterUseCase(
    private val repository: LocationRepository
) {
    suspend operator fun invoke(
        character: DetailedCharacterFirebase
    ) = repository.addLocation(character)
}