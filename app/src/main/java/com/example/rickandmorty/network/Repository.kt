package com.example.rickandmorty.network

import com.example.rickandmorty.charactersInfo.CharactersList
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class Repository @Inject constructor (private val RAMApi: RAMApiService) {
    fun getCharacters(page: Int): Deferred<CharactersList> {
        return RAMApi.getCharacters(page)
    }
}