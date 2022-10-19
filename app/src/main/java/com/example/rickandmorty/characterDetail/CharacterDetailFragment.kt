package com.example.rickandmorty.characterDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.network.Repository

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
           // detailOriginNameText.text = character.origin.name
          //  detailLocationNameText.text = character.location.name

            Glide.with(detailImage)
                .load(character.image)
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(detailImage)
            /*binding.addToFav.setOnClickListener {
                viewModel.addToFavorite(character)
            }*/
            binding.addToFav.setOnClickListener {
                viewModel.addToFavorite(character)
                viewModel.update(character)
            }
        }
    }
}
