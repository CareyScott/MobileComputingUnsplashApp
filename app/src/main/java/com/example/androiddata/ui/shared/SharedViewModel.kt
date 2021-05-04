package com.example.androiddata.ui.shared

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.androiddata.data.Photo
import com.example.androiddata.data.photoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SharedViewModel(app: Application) : AndroidViewModel(app) {

    // view model can be shared among all of the files

    private val dataRepo = photoRepository(app)
    val photoData = dataRepo.photoData

    // val selectedPhoto = MutableLiveData<Photo>()

    lateinit var selectedPhoto: Photo

    // when the user launches or manually refreshes, pull from API
    fun refreshData() {
    dataRepo.refreshDataFromWeb()
    }

    var photosResponse = dataRepo.photosResponse

    fun getPhotos(searchQuery: String) {
        CoroutineScope(Dispatchers.IO).launch {
            dataRepo.searchPhotos(searchQuery)
            photosResponse = dataRepo.photosResponse
        }
    }
}
