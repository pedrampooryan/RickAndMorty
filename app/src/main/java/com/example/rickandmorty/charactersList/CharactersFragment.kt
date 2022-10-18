package com.example.rickandmorty.charactersList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    /*private val viewModel: CharactersViewModel by viewModels{CharactersViewModelFactory(
        Repository()
    )  }*/
    private val viewModel: CharactersViewModel by activityViewModels()

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
        OnPageBottom().getOnPageBottom(binding.RecyclerView) {
            viewModel.getRAMCharacters()
        }

    }
}

