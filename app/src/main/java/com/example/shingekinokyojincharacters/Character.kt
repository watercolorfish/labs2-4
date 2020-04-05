package com.example.shingekinokyojincharacters

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/*
* mal_id
* url
* image_url
* name
* role
* voice_actors:
*   mal_id
*   name
*   url
*   image_url
*   language
* */

@Entity(tableName = "characters")
data class Character (

    /*
    Id for the character.
     */
    @PrimaryKey
    @SerializedName("mal_id")
    val id: String,
    /*
    The image url.
     */
    @SerializedName("imageUrl")
    val imageUrl: String?,
    /*
    The character's name.
     */
    @SerializedName("name")
    val name: String,
    /*
    The role of the character.
     */
    @SerializedName("role")
    val role: String
)