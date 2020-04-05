package com.example.shingekinokyojincharacters

import retrofit2.http.GET

interface CharactersApi {
    @GET("/v3/anime/16498/characters_staff")
    suspend fun getCharacters(): Characters
}