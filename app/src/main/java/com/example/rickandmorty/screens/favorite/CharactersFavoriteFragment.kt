package com.example.rickandmorty.screens.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersFavoriteBinding
import com.example.rickandmorty.viewbinding.viewBinding

class CharactersFavoriteFragment : Fragment(R.layout.fragment_characters_favorite) {

    private val binding by viewBinding(FragmentCharactersFavoriteBinding::bind)
    private val viewModel: CharactersFavoriteViewModel by activityViewModels()
    private val adapter = CharactersFavAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.RecyclerViewFavorite.adapter = adapter

        viewModel.favoriteChars.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            if (it.isEmpty()) {
                binding.clearAllButton.visibility = View.GONE
                binding.emptyFav.visibility = View.VISIBLE
            }
            else {
                binding.clearAllButton.visibility = View.VISIBLE
                binding.emptyFav.visibility = View.GONE
            }
        }

        binding.clearAllButton.setOnClickListener {
            viewModel.clearAllFavChars()
            Toast.makeText(context, "All Favorites Deleted!",Toast.LENGTH_SHORT).show()
        }
    }
}