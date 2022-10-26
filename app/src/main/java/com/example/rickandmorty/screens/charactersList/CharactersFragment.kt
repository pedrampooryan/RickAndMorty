package com.example.rickandmorty.screens.charactersList

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.extensions.onBottomReached
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private val viewModel: CharactersViewModel by viewModels()
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private val adapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            recyclerView.onBottomReached { viewModel.getRAMCharacters() }
        }

        //Show Characters
        viewModel.charactersList.observe(viewLifecycleOwner) { listChars ->
            adapter.submitList(listChars)
        }

        //Api Status
        viewModel.status.observe(viewLifecycleOwner) { netStatus ->
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
        }

        viewModel.characterTitle.observe(viewLifecycleOwner) {
            binding.charactersTitle.text = getString(R.string.character_title, it)

        }

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.filter_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.SHOW_ALL -> viewModel.setFilter(null)
                    R.id.JustFemale -> viewModel.setFilter(RAMApiFilter.FEMALE)
                    R.id.JustMale -> viewModel.setFilter(RAMApiFilter.MALE)
                    R.id.JustUnknown -> viewModel.setFilter(RAMApiFilter.UNKNOWN)
                    else -> viewModel.setFilter(null)
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}


