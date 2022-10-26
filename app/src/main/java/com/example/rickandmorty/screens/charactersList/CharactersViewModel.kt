package com.example.rickandmorty.screens.charactersList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.network.charactersInfo.CharactersProperty
import com.example.rickandmorty.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ApiStatus { Error, Loading, Done }
enum class RAMApiFilter(val value: String) {  MALE("male"), FEMALE("female"), UNKNOWN("unknown") }

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var page: Int = 1
    private var filter: RAMApiFilter? = null
    private var charsList = emptyList<CharactersProperty>()
        set(value) {
            field = value
            _charsListLive.value = charsList
        }

    private val _charsListLive = MutableLiveData<List<CharactersProperty>>()
    val charactersList: LiveData<List<CharactersProperty>>
        get() = _charsListLive


    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _characterTitle = MutableLiveData<String>()
    val characterTitle : LiveData<String>
    get () = _characterTitle

    init {
        getRAMCharacters()
        _characterTitle.value = "all"
    }

    fun setFilter(filter: RAMApiFilter?) {
        if (filter == this.filter) return
        this.filter = filter
        if (filter != null) { _characterTitle.value = filter.value}
        else {_characterTitle.value = "all" }
        page = 1
        charsList = emptyList()
        getRAMCharacters()
    }


    fun getRAMCharacters(){
        if (_status.value == ApiStatus.Loading)  return
        viewModelScope.launch {
            _status.value = ApiStatus.Loading

            try {
                val result = repository.getCharactersByGender(filter, page)
                page++
                charsList = charsList + result.results
                _status.value = ApiStatus.Done
            }
            catch (t: Throwable) {
                _status.value = ApiStatus.Error
            }
        }
    }
}