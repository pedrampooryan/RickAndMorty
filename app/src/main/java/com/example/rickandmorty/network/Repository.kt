package com.example.rickandmorty.network

import androidx.lifecycle.LiveData
import com.example.rickandmorty.database.AppDao
import com.example.rickandmorty.network.charactersInfo.CharactersProperty
import com.example.rickandmorty.screens.charactersList.RAMApiFilter
import javax.inject.Inject

class Repository @Inject constructor (private val RAMApi: RAMApiService,private val appDao: AppDao) {

    suspend fun getCharactersByGender(filter: RAMApiFilter?, page: Int) =
        RAMApi.getCharactersByGender(filter?.value, page)

    fun insert(character: CharactersProperty) {
        return appDao.insert(character)
    }

    fun getAllFavChars(): LiveData<List<CharactersProperty>> {
        return appDao.getAllFavChars()
    }

    fun clearAll() {
        appDao.clearAll()
    }

    fun deleteFav(id: Int) {
        appDao.deleteFromFav(id)
    }

    fun existID(id: Int): Boolean {
       return appDao.existID(id)
    }

}