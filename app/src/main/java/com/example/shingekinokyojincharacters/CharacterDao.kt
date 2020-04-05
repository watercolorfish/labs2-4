package com.example.shingekinokyojincharacters

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCharacters(characters: List<Character>)

    @Query("select * from characters")
    fun getAllCharacters(): List<Character>
}