package com.example.rickandmorty.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.network.RAMApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getRAMCharacters()
    }

    private fun getRAMCharacters() {
        coroutineScope.launch {
            var getCharactersDeferred = RAMApi.retrofitService.getCharacters()
            try {
                var result = getCharactersDeferred.await()
                _response.value = result.results.toString()
            }
            catch(t:Throwable) {
                _response.value = "Fail = " + t.message
            }

        }

    }

        /*RAMApi.retrofitService.getCharacters().enqueue(object : Callback<CharactersList> {
            override fun onResponse(call: Call<CharactersList>, response: Response<CharactersList>) {
                _response.value = response.body()?.results.toString()
            }

            override fun onFailure(call: Call<CharactersList>, t: Throwable) {
                _response.value = "Fail = " + t.message
            }
        })*/




}