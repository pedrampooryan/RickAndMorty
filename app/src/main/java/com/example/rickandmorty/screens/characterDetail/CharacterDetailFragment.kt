package com.example.rickandmorty.screens.characterDetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.viewbinding.viewBinding

class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    private val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    private val viewModel : CharacterDetailViewModel by activityViewModels()
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val character = args.character

        binding.apply {
            detailIdText.text = character.id.toString()
            detailNameText.text = getString(R.string.quotation, character.name)
            detailSpecieText.text = getString(R.string.Species, character.species)
            detailGenderText.text = getString(R.string.Gender, character.gender)
            detailStatusText.text = getString(R.string.Status, character.status)
            Glide.with(detailImage)
                .load(character.image)
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(detailImage)
        }

        viewModel.existID(character.id)
        viewModel.favStatus.observe(viewLifecycleOwner) { status ->
            if (status == FavStatus.ADDED) { binding.heartImage.setImageResource(R.drawable.redheart) }
            else { binding.heartImage.setImageResource(R.drawable.whiteheart) }
        }

        binding.heartImage.setOnClickListener {
            if (viewModel.favStatus.value == FavStatus.REMOVED) {
                viewModel.addToFavorite(character)
                viewModel.existID(character.id)
                Toast.makeText(context, "${character.name} Added To Favorite.",Toast.LENGTH_SHORT).show()
            } else {
                viewModel.deleteFav(character.id)
                viewModel.existID(character.id)
                Toast.makeText(context, "${character.name} Removed From Favorite.",Toast.LENGTH_SHORT).show()

            }
        }
    }
}