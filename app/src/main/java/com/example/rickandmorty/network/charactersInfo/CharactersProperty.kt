package com.example.rickandmorty.network.charactersInfo

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Favorite_chars_db_table")
data class CharactersProperty(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int=0,

    @ColumnInfo(name = "name")
    val name: String="rick",

    @ColumnInfo(name = "status")
    val status: String="alivev",

    @ColumnInfo(name = "species")
    val species: String="human",

    @ColumnInfo(name = "gender")
    val gender: String="male",

    @ColumnInfo(name = "image")
    val image: String="ii",

): Parcelable

data class CharactersList( val results: List<CharactersProperty> )
