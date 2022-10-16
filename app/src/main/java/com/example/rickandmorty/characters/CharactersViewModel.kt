package com.example.rickandmorty.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.charactersInfo.CharactersProperty
import com.example.rickandmorty.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ApiStatus { Error, Loading, Done }

@HiltViewModel
class CharactersViewModel @Inject constructor (private val repository: Repository) : ViewModel() {


    var charsList = emptyList<CharactersProperty>()

    private val _charsListLive = MutableLiveData<List<CharactersProperty>>()
    val charactersList: LiveData<List<CharactersProperty>>
        get() = _charsListLive


    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status


    fun getRAMCharacters(page: Int) {
        viewModelScope.launch {
            _status.value = ApiStatus.Loading

            try {
                val getCharactersDeferred = repository.getCharacters(page)
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