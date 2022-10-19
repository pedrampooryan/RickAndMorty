package com.example.rickandmorty.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.charactersInfo.CharactersProperty
import com.example.rickandmorty.charactersList.CharactersAdapter
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
    ): View? {
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

        /*binding.clearAllButton.setOnClickListener {
            viewModel.clearAllFavChars()
        }*/
        binding.clearAllButton.setOnClickListener {
            viewModel.clearAllFavChars()
        }

    }

}