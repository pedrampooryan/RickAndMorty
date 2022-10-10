package com.example.rickandmorty.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.network.RAMApi
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
        RAMApi.retrofitService.getCharacters().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Fail" + t.message
            }
        })

    }


}