package com.example.shingekinokyojincharacters

import android.app.Application
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersApplication : Application() {
    var charactersService: CharactersApi? = null

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl("https://api.jikan.moe")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        charactersService = retrofit.create(CharactersApi::class.java)
    }
}
