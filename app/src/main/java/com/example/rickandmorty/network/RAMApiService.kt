package com.example.rickandmorty.network

import com.example.rickandmorty.characters.CharactersList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val Base_URL = "https://rickandmortyapi.com/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Base_URL)
    .build()

interface RAMApiService {
    @GET("api/character")
    fun getCharacters(): Call<CharactersList>
}

object RAMApi {
    val retrofitService:RAMApiService by lazy { retrofit.create(RAMApiService::class.java)}
}