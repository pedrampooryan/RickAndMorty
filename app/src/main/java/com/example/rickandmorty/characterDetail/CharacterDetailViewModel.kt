package com.example.rickandmorty.characterDetail

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.charactersInfo.CharactersProperty
import com.example.rickandmorty.network.Repository
import javax.inject.Inject
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

enum class FavStatus { ADDED, REMOVED }
@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _favStatus = MutableLiveData<FavStatus>()
    val favStatus: LiveData<FavStatus>
    get() = _favStatus


    fun addToFavorite(character:CharactersProperty){
        viewModelScope.launch {
            repository.insert(character)
        }
    }
    fun update(character: CharactersProperty) {
        repository.update(character)
    }

    fun deleteFav(id: Int) {
        repository.deleteFav(id)
    }

    fun existID(id: Int) {
        val result =  repository.existID(id)
        if (result == true) {
            _favStatus.value = FavStatus.ADDED
        }
        else {
            _favStatus.value = FavStatus.REMOVED
        }
    }

}