package com.example.rickandmorty.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.network.RAMApi
import com.example.rickandmorty.network.RAMCharactersList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getRAMCharacters()
    }

    private fun getRAMCharacters() {
            RAMApi.retrofitService.getCharacters().enqueue(object : Callback<List<RAMCharactersList>> {
            override fun onResponse(call: Call<List<RAMCharactersList>>, response: Response<List<RAMCharactersList>>) {
                _response.value = "${response.body()}"
            }

            override fun onFailure(call: Call<List<RAMCharactersList>>, t: Throwable) {
                _response.value = "Fail" + t.message
            }
        })

    }


}