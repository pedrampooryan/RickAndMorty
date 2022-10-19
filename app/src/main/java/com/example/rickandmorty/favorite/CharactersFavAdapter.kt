package com.example.rickandmorty.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.charactersInfo.CharactersProperty
import com.example.rickandmorty.charactersList.CharactersAdapter
import com.example.rickandmorty.charactersList.CharactersFragmentDirections
import com.example.rickandmorty.databinding.CharacterViewBinding

class CharactersFavAdapter :
    ListAdapter<CharactersProperty, CharactersFavAdapter.CharViewHolder>(CharsDiffCallback()) {

    class CharViewHolder(private val binding: CharacterViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: CharactersProperty) {
            binding.characterIdText.text = character.id.toString()
            binding.characterNameText.text = character.name
            /* Picasso.get().load(character.image)
                 .placeholder(R.drawable.loading_animation)
                 .error(R.drawable.ic_broken_image)
                 .into(binding.characterImage)*/
            Glide.with(binding.characterImage.context)
                .load(character.image)
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(binding.characterImage)
            itemView.setOnClickListener {view ->
                val action = CharactersFavoriteFragmentDirections.actionCharactersFavoriteFragmentToCharacterDetailFragment2(character)
                view.findNavController().navigate(action)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersFavAdapter.CharViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = CharacterViewBinding.inflate(layoutInflater, parent, false)
        return CharactersFavAdapter.CharViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersFavAdapter.CharViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class CharsDiffCallback : DiffUtil.ItemCallback<CharactersProperty>() {
        override fun areItemsTheSame(
            oldItem: CharactersProperty,
            newItem: CharactersProperty
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharactersProperty,
            newItem: CharactersProperty
        ): Boolean {
            return oldItem == newItem
        }
    }

    /* override fun getItemCount(): Int {
      return listCharacters.size
  }*/


    /*fun setCharacters(characters: List<CharactersProperty>) {
        listCharacters = characters
        notifyDataSetChanged()
    }*/
}