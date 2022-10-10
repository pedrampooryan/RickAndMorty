package com.example.rickandmorty.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val Base_URL = "https://rickandmortyapi.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(Base_URL)
    .build()

interface RAMApiService {
    @GET("api/character")
    fun getCharacters(): Call<String>
}

object RAMApi {
    val retrofitService:RAMApiService by lazy { retrofit.create(RAMApiService::class.java)}
}