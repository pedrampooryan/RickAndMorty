package com.example.rickandmorty.screens.characterDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding

class CharacterDetailFragment : Fragment() {

    private val viewModel : CharacterDetailViewModel by activityViewModels()

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

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