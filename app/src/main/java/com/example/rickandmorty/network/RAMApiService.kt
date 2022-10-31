package com.example.rickandmorty.network

import com.example.rickandmorty.network.charactersInfo.CharactersList
import retrofit2.http.GET
import retrofit2.http.Query

interface RAMApiService {

    @GET("api/character")
    suspend fun getCharactersByGender(
        @Query("gender") filter: String?,
        @Query("page") page: Int
    ): CharactersList

}

