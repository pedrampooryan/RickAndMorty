package com.example.rickandmorty.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import java.util.Objects


class CharactersFragment : Fragment() {

    private var page: Int = 1
    private var t: Int = 1

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this)[CharactersViewModel::class.java]
    }

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private val adapter = CharactersAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.RecyclerView.adapter = adapter



        binding.RecyclerView.layoutManager = GridLayoutManager(context, 2)

        //Show Characters
        viewModel.charactersList.observe(viewLifecycleOwner, Observer { listChars ->
            adapter.submitList(listChars)

        })


        //Api Status
        viewModel.status.observe(viewLifecycleOwner, Observer { netStatus ->
            when (netStatus!!) {
                ApiStatus.Loading -> {
                    binding.statusImg.visibility = View.VISIBLE
                    binding.statusImg.setImageResource(R.drawable.loading_animation)
                }
                ApiStatus.Error -> {
                    binding.statusImg.visibility = View.VISIBLE
                    binding.statusImg.setImageResource(R.drawable.ic_connection_error)
                }
                ApiStatus.Done -> binding.statusImg.visibility = View.GONE
            }
        })

        // get Characters using pagination
        OnPageBottom().getOnPageBottom(binding.RecyclerView, object : AddGetDataFunction {
            override fun getData(page: Int) {
                viewModel.getRAMCharacters(page)
            }
        })
    }
}