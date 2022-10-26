package com.example.rickandmorty.screens.characterDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.network.charactersInfo.CharactersProperty
import com.example.rickandmorty.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class FavStatus { ADDED, REMOVED }
@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _favStatus = MutableLiveData<FavStatus>()
    val favStatus: LiveData<FavStatus>
    get() = _favStatus

    fun addToFavorite(character: CharactersProperty){
        viewModelScope.launch {
            repository.insert(character)
        }
    }

    fun deleteFav(id: Int) {
        repository.deleteFav(id)
    }

    fun existID(id: Int) {
        val result =  repository.existID(id)
        if (result) {
            _favStatus.value = FavStatus.ADDED
        }
        else {
            _favStatus.value = FavStatus.REMOVED
        }
    }
}