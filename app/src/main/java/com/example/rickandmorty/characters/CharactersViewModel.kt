package com.example.rickandmorty.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.charactersInfo.CharactersList
import com.example.rickandmorty.network.RAMApi
import kotlinx.coroutines.*

enum class ApiStatus { Error, Loading, Done }

class CharactersViewModel : ViewModel() {

    private val _response = MutableLiveData<CharactersList>()
    val response: LiveData<CharactersList>
        get() = _response

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getRAMCharacters()
    }

    private fun getRAMCharacters() {
        coroutineScope.launch {
            _status.value = ApiStatus.Loading
            try {
                val getCharactersDeferred = RAMApi.retrofitService.getCharacters()
                val result = getCharactersDeferred.await()
                _response.value = result
                _status.value = ApiStatus.Done
            } catch (t: Throwable) {
                _status.value = ApiStatus.Error
            }

        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}