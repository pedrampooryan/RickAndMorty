package com.example.rickandmorty.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersBinding


class CharactersFragment : Fragment() {

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

        binding.RecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        viewModel.response.observe(viewLifecycleOwner, Observer { listChars ->
            adapter.setCharacters(listChars.results)

        })

        viewModel.status.observe(viewLifecycleOwner, Observer { netStatus ->
            when (netStatus) {
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

        /*viewModel.response.observe(viewLifecycleOwner, Observer { responseString ->
            Glide.with(this)
                .load(responseString)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(binding.testImage)

        })*/

    }


    /*fun showImage(testImage: ImageView, imgUrl: String? = _response.value) {
        if (_response.value !== null) {
            Glide.with(testImage.context)
                .load(imgUrl)
                .into(testImage)
        } else {
            imageview.setImageResource(R.drawable.ic_launcher_background)
        }
*/


}