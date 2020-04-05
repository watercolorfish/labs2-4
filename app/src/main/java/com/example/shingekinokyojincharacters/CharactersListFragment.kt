package com.example.shingekinokyojincharacters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersListFragment : Fragment(), CharactersAdapter.OnItemClickListener {
    var charactersAdapter: CharactersAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val applicationContext = activity?.applicationContext!!

        val characterApi = (activity?.application as CharactersApplication).charactersService
        val db = Room.databaseBuilder(applicationContext, CharacterDatabase::class.java, "characters.db")
            .fallbackToDestructiveMigration()
            .build()
        val characterDao = db.getCharacterDao()

        fail_loading.visibility = View.GONE
        progress_bar.visibility = View.GONE

        charactersAdapter = CharactersAdapter(applicationContext, emptyList(), this)
        recycler_view.adapter = charactersAdapter
        recycler_view.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL, false
        )

        load_button.setOnClickListener {

            recycler_view.visibility = View.INVISIBLE
            fail_loading.visibility = View.GONE
            progress_bar.visibility = View.VISIBLE

            GlobalScope.launch {

                var characters: List<Character>? = emptyList()

                try {
                    val result = characterApi?.getCharacters()

                    characters = result?.characters
                    if (characters != null) {
                        characterDao.addCharacters(characters)
                    }

                } catch (e: Exception) {
                    Log.d("CharacterApi", "Failed to load characters from the remote", e)

                    characters = characterDao.getAllCharacters()

                } finally {

                    if (characters == null || characters.isEmpty()) {
                        withContext(Dispatchers.Main) {
                            fail_loading.visibility = View.VISIBLE
                        }
                    } else {
                        charactersAdapter?.characters = characters
                        withContext(Dispatchers.Main) {
                            recycler_view.visibility = View.VISIBLE
                        }
                    }
                }

                withContext(Dispatchers.Main) {
                    progress_bar.visibility = View.GONE
                }
            }
        }
    }

    override fun onItemClick(character: Character) {
        (activity as? MainActivity)?.onCharacterClick(character)
    }
}