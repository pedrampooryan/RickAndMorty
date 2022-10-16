package com.example.rickandmorty.network

import com.example.rickandmorty.charactersInfo.CharactersList
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
    fun getCharacters(@Query("page") page: Int): Deferred<CharactersList>
}

