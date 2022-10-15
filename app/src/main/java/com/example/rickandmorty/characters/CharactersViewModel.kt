package com.example.rickandmorty.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.charactersInfo.CharactersList
import com.example.rickandmorty.charactersInfo.CharactersProperty
import com.example.rickandmorty.network.RAMApi
import kotlinx.coroutines.*

enum class ApiStatus { Error, Loading, Done }

class CharactersViewModel() : ViewModel() {


    var charsList = emptyList<CharactersProperty>()

    private val _charsListLive = MutableLiveData<List<CharactersProperty>>()
    val charactersList: LiveData<List<CharactersProperty>>
        get() = _charsListLive


    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

   /* private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }*/

    fun getRAMCharacters(page: Int) {
        viewModelScope.launch {
            _status.value = ApiStatus.Loading

            try {
                val getCharactersDeferred = RAMApi.retrofitService.getCharacters(page)
                val result = getCharactersDeferred.await()
                charsList = charsList + result.results
                _charsListLive.value = charsList

                _status.value = ApiStatus.Done
            } catch (t: Throwable) {
                _status.value = ApiStatus.Error
            }
        }
    }
}