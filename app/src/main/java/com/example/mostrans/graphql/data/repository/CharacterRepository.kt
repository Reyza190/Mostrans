package com.example.mostrans.graphql.data.repository

import com.example.mostrans.graphql.domain.model.DetailedCharacter
import com.example.mostrans.graphql.domain.model.SimpleCharacter

interface CharacterRepository {
    suspend fun getCharacters(): List<SimpleCharacter>
    suspend fun getCharacter(id: String): DetailedCharacter?
}