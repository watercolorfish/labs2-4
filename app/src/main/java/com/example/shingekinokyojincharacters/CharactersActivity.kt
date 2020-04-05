package com.example.shingekinokyojincharacters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CharactersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CharactersListFragment())
            .commit()
    }

    fun onCardClick(character: Character) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, CharacterFragment(character), null)
            .addToBackStack("CharacterFragment")
            .commit()
    }
}