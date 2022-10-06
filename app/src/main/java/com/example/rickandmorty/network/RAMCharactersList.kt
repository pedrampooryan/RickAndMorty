package com.example.rickandmorty.network

data class RAMCharactersList(
    val results: List<RAMCharProperty>
)

data class RAMCharProperty(
    val id: Int,
    val name: String,
    val status: String,
    val gender: String,
    val origin: OriginName,
    val location: LocationName,
    val image: String,
    val episode: List<String>
)

data class OriginName(
    val name: String,
)

data class LocationName(
    val name: String
)
