package com.example.shingekinokyojincharacters

import com.google.gson.annotations.SerializedName

data class Characters (
    @SerializedName("characters")
    val characters: List<Character>?
)