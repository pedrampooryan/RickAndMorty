package com.example.rickandmorty.screens.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.network.charactersInfo.CharactersProperty
import com.example.rickandmorty.screens.charactersList.CharactersAdapter
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.databinding.FragmentCharactersFavoriteBinding

class CharactersFavoriteFragment : Fragment() {

    private val viewModel: CharactersFavoriteViewModel by activityViewModels()
    private val adapter = CharactersFavAdapter()
    private var _binding: FragmentCharactersFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.RecyclerViewFavorite.adapter = adapter

        viewModel.favoriteChars.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            if (it.isEmpty()) {
                binding.clearAllButton.visibility = View.GONE
                binding.emptyFav.visibility = View.VISIBLE
            }
            else {
                binding.clearAllButton.visibility = View.VISIBLE
                binding.emptyFav.visibility = View.GONE
            }
        })

        binding.clearAllButton.setOnClickListener {
            viewModel.clearAllFavChars()
            Toast.makeText(context, "All Favorites Deleted!",Toast.LENGTH_SHORT).show()
        }
    }
}