package com.example.mostrans.di

import com.apollographql.apollo3.ApolloClient
import com.example.mostrans.firebase.data.repository.LocationRepository
import com.example.mostrans.firebase.data.repository.LocationRepositoryImpl
import com.example.mostrans.firebase.domain.usecase.AddLocationCharacterUseCase
import com.example.mostrans.firebase.domain.usecase.GetLocationCharacterUseCase
import com.example.mostrans.firebase.domain.usecase.UseCases
import com.example.mostrans.graphql.data.repository.CharacterRepositoryImpl
import com.example.mostrans.graphql.data.repository.CharacterRepository
import com.example.mostrans.graphql.domain.usecase.GetCharacterUseCase
import com.example.mostrans.graphql.domain.usecase.GetCharactersUseCase
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient{
        return ApolloClient.Builder()
          .serverUrl("https://rickandmortyapi.com/graphql")
          .build()
    }

    @Provides
    @Singleton
    fun provideCharacterClient(apollo: ApolloClient): CharacterRepository {
        return CharacterRepositoryImpl(apollo)
    }

    @Provides
    @Singleton
    fun provideCharacterUseCase(characterRepository: CharacterRepository): GetCharacterUseCase {
        return GetCharacterUseCase(characterRepository)
    }

    @Provides
    @Singleton
    fun provideCharactersUseCase(charactersClient: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCase(charactersClient)
    }

    @Provides
    fun provideBooksRef() = Firebase.firestore.collection("Character")

    @Provides
    fun provideBooksRepository(
        characterRef: CollectionReference
    ): LocationRepository = LocationRepositoryImpl(characterRef)

    @Provides
    fun provideUseCases(
        repo: LocationRepository
    ) = UseCases(
        getLocationCharacter = GetLocationCharacterUseCase(repo),
        addLocation = AddLocationCharacterUseCase(repo),
    )
}