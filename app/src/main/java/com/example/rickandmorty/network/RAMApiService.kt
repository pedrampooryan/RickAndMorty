package com.example.rickandmorty.network

import com.example.rickandmorty.charactersInfo.CharactersList
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val Base_URL = "https://rickandmortyapi.com/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(Base_URL)
    .build()

interface RAMApiService {
    @GET("api/character")
    fun getCharacters(): Deferred<CharactersList>
}

object RAMApi {
    val retrofitService:RAMApiService by lazy { retrofit.create(RAMApiService::class.java)}
}