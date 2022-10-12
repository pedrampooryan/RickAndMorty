package com.example.rickandmorty.characters

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.rickandmorty.charactersInfo.CharactersList
import com.example.rickandmorty.charactersInfo.CharactersProperty
import com.example.rickandmorty.network.RAMApi
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersViewModel : ViewModel() {

    private val _response = MutableLiveData<List<CharactersProperty>>()
    val response: LiveData<List<CharactersProperty>>
        get() = _response

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getRAMCharacters()
    }

    private fun getRAMCharacters() {
        coroutineScope.launch {
            val getCharactersDeferred = RAMApi.retrofitService.getCharacters()
           // try {
                val result = getCharactersDeferred.await()
                _response.value = result.results
           // }
          /*  catch(t:Throwable) {
                _response.value = "Fail = " + t.message
            }*/

        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



}