package com.example.androiddata.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.androiddata.LOG_TAG
import com.example.androiddata.R
import com.example.androiddata.databinding.FragmentDetailBinding
import com.example.androiddata.ui.shared.SharedViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {

//        val view: View = inflater.inflate(R.layout.fragment_detail, container, false)
//        view.facebookBtn.setOnClickListener {
//            Log.i(LOG_TAG, "Selected")
//        }

        // this code is for implementing the back button when in the display fragment
        (requireActivity()as AppCompatActivity).run{
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)
        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )
        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)

//        viewModel.selectedPhoto.observe(this, Observer {
//            Log.i(LOG_TAG, "selected Photo: ${it.id}")
//        })



        val binding = FragmentDetailBinding.inflate(
            inflater, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // photo logging the users name in logcat widow
        Log.i("Detail", "Name is" + viewModel.selectedPhoto.user.name)
        Log.i("Detail", "In Detail")
        //binding = DetailFragmentBinding
//        return inflater.inflate(R.layout.fragment_detail, container, false)
        return binding.root


//        userButton.setOnClickListener({
//            val i = Intent(Intent.ACTION_VIEW, Uri.parse("${viewModel.selectedPhoto.user.name}"))
//            startActivity(i)
//        })




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // when this button is clicked, the unsplash page is loaded wihin the phones default browser
        unsplashBtn.setOnClickListener { view ->
            Log.i(LOG_TAG, "${viewModel.selectedPhoto.user.links.html}Selected")
            // full link pulled from nested object in the api
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("${viewModel.selectedPhoto.user.links.html}"))
            startActivity(i)
        }

        // when this button is clicked, the users instagram page is loaded within the phones default browser parses the link

        instagramBtn.setOnClickListener { view ->
            Log.i(LOG_TAG,  "image = ${viewModel.selectedPhoto.user}")
            // half hardcoded link + other half pulled from api
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/${viewModel.selectedPhoto.user.instagram_username}"))
            startActivity(i)
        }
//        twitterBtn.setOnClickListener { view ->
//            Log.i(LOG_TAG,  "image = ${viewModel.selectedPhoto.user}")
//            // half hardcoded link + other half pulled from api
//            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/${viewModel.selectedPhoto.user.twitter_username}"))
//            startActivity(i)
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home){
            navController.navigateUp()
            // navigates back to the main fragment

        }
        return super.onOptionsItemSelected(item)
    }



//    override fun onButtonClick(photo: Photo) {
//        Log.i(LOG_TAG, "Selected User ${photo.user.name}" )
//        viewModel.selectedPhoto = photo
//        val i = Intent(Intent.ACTION_VIEW, Uri.parse("${viewModel.selectedPhoto.user.name}"))
//        startActivity(i)
//    }


}