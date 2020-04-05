package com.example.shingekinokyojincharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CharactersListFragment())
            .commit()
    }

    fun onCharacterClick(character: Character) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, CharacterFragment(character), null)
            .addToBackStack("CharacterFragment")
            .commit()
    }
}