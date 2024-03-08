package com.example.mostrans.graphql.data.mappers

import com.example.CharacterQuery
import com.example.CharactersQuery
import com.example.mostrans.graphql.domain.model.DetailedCharacter
import com.example.mostrans.graphql.domain.model.SimpleCharacter

fun CharacterQuery.Character.toDetailedCharacter(): DetailedCharacter {
    return DetailedCharacter(
        id = id,
        name = name,
        image = image,
        species = species,
        status = status,
        gender = gender
    )
}

fun CharactersQuery.Result.toSimpleCharacter(): SimpleCharacter {
    return SimpleCharacter(
        id = id,
        name = name,
        image = image,
        species = species,
    )
}