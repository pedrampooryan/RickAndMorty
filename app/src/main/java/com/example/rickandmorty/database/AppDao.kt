package com.example.rickandmorty.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.rickandmorty.charactersInfo.CharactersProperty


@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(character: CharactersProperty)

    @Update
    fun update(character: CharactersProperty)

    @Query("DELETE FROM favorite_chars_db_table ")
    fun clearAll()

    @Query("SELECT * FROM favorite_chars_db_table ORDER BY id ASC")
    fun getAllFavChars(): LiveData<List<CharactersProperty>>


}