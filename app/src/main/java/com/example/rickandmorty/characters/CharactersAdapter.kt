package com.example.rickandmorty.characters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.charactersInfo.CharactersList
import com.example.rickandmorty.charactersInfo.CharactersProperty
import com.example.rickandmorty.databinding.CharacterViewBinding
import com.squareup.picasso.Picasso


class CharactersAdapter() : RecyclerView.Adapter<CharactersAdapter.CharViewHolder>() {

    private var listCharacters = emptyList<CharactersProperty>()

    class CharViewHolder(private val binding: CharacterViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: CharactersProperty) {
            binding.characterIdText.text = character.id.toString()
            binding.characterNameText.text = character.name
            Picasso.get().load(character.image)
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(binding.characterImage)
            /*Glide.with(Activity())
                .load(character.image)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(binding.characterImage)*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = CharacterViewBinding.inflate(layoutInflater, parent, false)
        return CharViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharViewHolder, position: Int) {
        holder.bind(listCharacters[position])
    }

    override fun getItemCount(): Int {
        return listCharacters.size
    }

    fun setCharacters(characters: List<CharactersProperty>) {
        listCharacters = characters
        notifyDataSetChanged()
    }
}