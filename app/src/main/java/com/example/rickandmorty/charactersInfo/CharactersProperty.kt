package com.example.rickandmorty.charactersInfo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
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

): Parcelable

data class CharactersList(
    val results: List<CharactersProperty>
)
@Parcelize
data class LocOriName(
    val name: String
): Parcelable


