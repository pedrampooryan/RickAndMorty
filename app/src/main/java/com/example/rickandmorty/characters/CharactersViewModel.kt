package com.example.rickandmorty.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CharactersViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getRAMCharacters()
    }

    private fun getRAMCharacters() {
        _response.value = "Hi Rick"
    }


}