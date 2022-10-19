package com.example.rickandmorty.characterDetail

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.charactersInfo.CharactersProperty
import com.example.rickandmorty.network.Repository
import javax.inject.Inject
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {



    fun addToFavorite(character:CharactersProperty){
        viewModelScope.launch {
            repository.insert(character)
        }
    }
    fun update(character: CharactersProperty) {
        repository.update(character)
    }
}