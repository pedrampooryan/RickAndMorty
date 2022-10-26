package com.example.rickandmorty.screens.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.network.charactersInfo.CharactersProperty
import com.example.rickandmorty.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersFavoriteViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    lateinit var favoriteChars: LiveData<List<CharactersProperty>>

    init {
        getAllFavChars()
    }

    private fun getAllFavChars() {
        viewModelScope.launch {
           favoriteChars = repository.getAllFavChars()
        }
    }

    fun clearAllFavChars() {
        repository.clearAll()
    }
}