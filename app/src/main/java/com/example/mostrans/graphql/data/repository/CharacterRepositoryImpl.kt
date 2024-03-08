package com.example.mostrans.graphql.data.repository

import com.apollographql.apollo3.ApolloClient
import com.example.CharacterQuery
import com.example.CharactersQuery
import com.example.mostrans.graphql.data.mappers.toDetailedCharacter
import com.example.mostrans.graphql.data.mappers.toSimpleCharacter
import com.example.mostrans.graphql.domain.model.DetailedCharacter
import com.example.mostrans.graphql.domain.model.SimpleCharacter

class CharacterRepositoryImpl(
    private val apolloClient: ApolloClient
) : CharacterRepository {
    override suspend fun getCharacters(): List<SimpleCharacter> {
        return apolloClient
            .query(CharactersQuery())
            .execute()
            .data
            ?.characters
            ?.results
            ?.map { it!!.toSimpleCharacter() }
            ?: emptyList()

    }

    override suspend fun getCharacter(id: String): DetailedCharacter? {
        return apolloClient
            .query(CharacterQuery(id))
            .execute()
            .data
            ?.character
            ?.toDetailedCharacter()
    }
}