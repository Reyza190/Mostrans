package com.example.mostrans.firebase.domain.usecase

import com.example.mostrans.firebase.data.repository.LocationRepository

class GetLocationCharacterUseCase(
    private val repository: LocationRepository
) {
    operator fun invoke() = repository.getLocations()
}