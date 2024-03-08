package com.example.mostrans.firebase.data.repository

import com.example.mostrans.firebase.domain.model.DetailedCharacterFirebase
import com.example.mostrans.graphql.domain.model.DetailedCharacter
import com.example.mostrans.graphql.domain.model.SimpleCharacter
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepositoryImpl @Inject constructor(
    private val characterRef: CollectionReference
) : LocationRepository{
    override fun getLocations() = callbackFlow {
        val snapshotListener = characterRef.orderBy("title").addSnapshotListener { snapshot, e ->
            val characterResponse = if (snapshot != null) {
                val characters = snapshot.toObjects(SimpleCharacter::class.java)
                Response.Success(characters)
            } else {
                Response.Failure(e)
            }
            trySend(characterResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addLocation(locationCharacter: DetailedCharacterFirebase)= try {
        val id = characterRef.document().id
        val character = DetailedCharacterFirebase(
            id = id,
            name = locationCharacter.name,
            image = locationCharacter.image,
            species = locationCharacter.species,
            status = locationCharacter.status,
            gender = locationCharacter.gender,
            location = locationCharacter.location
        )
        characterRef.document(id).set(character).await()
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e)
    }
}