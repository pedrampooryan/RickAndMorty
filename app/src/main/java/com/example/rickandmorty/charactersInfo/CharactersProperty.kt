package com.example.rickandmorty.charactersInfo

data class CharactersProperty(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: LocOriName,
    val location: LocOriName,
    val image: String,
    val episode: List<String>

)
