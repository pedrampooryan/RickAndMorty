package com.example.rickandmorty.network

import com.example.rickandmorty.network.charactersInfo.CharactersList
import com.example.rickandmorty.screens.charactersList.RAMApiFilter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RAMApiService {

    @GET("api/character")
    suspend fun getCharactersByGender(
        @Query("gender") filter: String?,
        @Query("page") page: Int
    ): CharactersList

}

