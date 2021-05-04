package com.example.androiddata.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.androiddata.LOG_TAG
import com.example.androiddata.R
import com.example.androiddata.data.Photo
import com.example.androiddata.ui.shared.SharedViewModel
//import com.example.androiddata.databinding


class MainFragment : Fragment(), MainRecyclerAdapter.PhotoItemListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: SharedViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity()as AppCompatActivity).run{
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )
        swipeLayout = view.findViewById(R.id.swipeLayout)
        swipeLayout.setOnRefreshListener {
            viewModel.refreshData()
        }

        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)
        viewModel.photoData.observe(this, Observer {
        val adapter = MainRecyclerAdapter(requireContext(), it, this)
            recyclerView.adapter = adapter
            swipeLayout.isRefreshing = false
        })
        return view
    }

    // when a photo is clicked, navigate to the detail page, passing in the photo object for the detail fragment to display data
    override fun onPhotoItemClick(photo: Photo) {
        Log.i(LOG_TAG, "Selected Photo ${photo.id}" )
        viewModel.selectedPhoto = photo
//        val action = MainFragmentDirections.action
        navController.navigate(R.id.action_nav_detail)
    }




}
