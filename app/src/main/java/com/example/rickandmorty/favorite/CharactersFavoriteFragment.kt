package com.example.rickandmorty.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.databinding.FragmentCharactersFavoriteBinding

class CharactersFavoriteFragment : Fragment() {

    private var _binding: FragmentCharactersFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharactersFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

}