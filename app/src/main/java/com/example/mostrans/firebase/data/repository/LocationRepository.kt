package com.example.mostrans.firebase.data.repository

import com.example.mostrans.firebase.domain.model.DetailedCharacterFirebase
import com.example.mostrans.graphql.domain.model.DetailedCharacter
import com.example.mostrans.graphql.domain.model.SimpleCharacter
import kotlinx.coroutines.flow.Flow


typealias CharacterLocation = List<SimpleCharacter>
typealias CharacterResponse = Response<CharacterLocation>
typealias AddCharacterResponse = Response<Boolean>
interface LocationRepository {
    fun getLocations(): Flow<CharacterResponse>
    suspend fun addLocation(locationCharacter: DetailedCharacterFirebase): AddCharacterResponse
}