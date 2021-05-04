package com.example.androiddata.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.androiddata.R
import com.example.androiddata.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)
        //viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        // view?.findNavController()?.navigate(R.id.action_searchFragment_to_mainFragment, searchQuery)

        // WHen the Search Button in clicked
        binding.searchButton.setOnClickListener {
            Log.i("Search", binding.searchText.text.toString() )
            // take the search string from the binding object and send it as an argument into MainFragment
            // note the action and the argument passed in must be defined in nav_graph
            val action = SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(binding.searchText.text.toString())
            findNavController().navigate(action)
        }

        return binding.root
    }



}