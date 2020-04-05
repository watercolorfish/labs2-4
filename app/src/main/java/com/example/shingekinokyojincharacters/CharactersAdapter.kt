package com.example.shingekinokyojincharacters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.lang.Exception

class CharactersAdapter(private val context: Context,
    var characters: List<Character>,
    private val onItemClickListener: CharactersAdapter.OnItemClickListener
    ) : RecyclerView.Adapter<CharactersAdapter.Item>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
            val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
            return Item(view)
        }

        override fun getItemCount(): Int {
            return characters.size
        }

        override fun onBindViewHolder(holder: Item, position: Int) {
            val character = characters[position]

            holder.name.text = character.name

            holder.itemView.setOnClickListener { onItemClickListener.onItemClick(character) }

            try {
                Glide.with(context)
                    .load(character.imageUrl)
                    .placeholder(R.drawable.download)
                    .fallback(R.drawable.empty)
                    .error(R.drawable.empty)
                    .into(holder.image)
            } catch (e: Exception) {
                Log.d("Glide", "Failed to load image", e)
            }
        }

        interface OnItemClickListener {
            fun onItemClick(character: Character)
        }

        class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val image: ImageView = itemView.findViewById(R.id.item_image_view)
            val name: TextView = itemView.findViewById(R.id.item_name_view)
        }
}