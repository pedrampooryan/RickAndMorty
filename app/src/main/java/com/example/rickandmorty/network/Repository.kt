package com.example.rickandmorty.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.charactersInfo.CharactersList
import com.example.rickandmorty.charactersInfo.CharactersProperty
import com.example.rickandmorty.database.AppDao
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class Repository @Inject constructor (private val RAMApi: RAMApiService,private val appDao: AppDao) {
    fun getCharacters(page: Int): Deferred<CharactersList> {
        return RAMApi.getCharacters(page)
    }
    fun insert(character: CharactersProperty) {
        return appDao.insert(character)
    }

    fun getAllFavChars(): LiveData<List<CharactersProperty>> {
        return appDao.getAllFavChars()
    }

    fun update(character: CharactersProperty){
        return appDao.update(character)
    }

    fun clearAll() {
        appDao.clearAll()
    }

}