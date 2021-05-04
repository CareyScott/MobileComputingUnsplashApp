package com.example.androiddata.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.androiddata.LOG_TAG
import com.example.androiddata.WEB_SERVICE_URL
import com.example.androiddata.utilities.fileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class photoRepository (val app: Application){

    val photosResponse = MutableLiveData<PhotosResponse>()

    val photoData = MutableLiveData<List<Photo>>()
    private val listType = Types.newParameterizedType(
        List::class.java, Photo::class.java)

    init {
        val data= readDataFromCache()
        if (data.isEmpty()){
            refreshDataFromWeb()
        }else{
            photoData.value = data
            Log.i(LOG_TAG, "Using Local Data")
        }
        
    }
// connects to the internet and uses the api link passing the info to the service data file
    @WorkerThread
       suspend fun callWebService () {
            if (networkAvailable()){
                Log.i(LOG_TAG, "calling web service")
                val retrofit = Retrofit.Builder()
                    .baseUrl(WEB_SERVICE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                val service = retrofit.create(PhotoService::class.java)
                val serviceData = service.getPhotoData().body() ?: emptyList()
                photoData.postValue(serviceData)
                saveDataToCache(serviceData)
            }
        }

    @WorkerThread
    suspend fun searchPhotos(searchQuery : String) {
        if (networkAvailable()) {
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            // Ask retrofit to create an implementation of the API endpoints (implements the functions in PlantService interface, these functional are the contact points with the API)
            val service = retrofit.create(PhotoService::class.java)


            // getPlantData() is defined in the WebService interface, Retrofit will have implemented this function for you
            val res  = service.simpleSearchPhotos(searchQuery)
            photosResponse.postValue(res)
            Log.i("HERE", photosResponse.toString())
        }
    }

    private fun networkAvailable(): Boolean{
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }

    fun refreshDataFromWeb() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }

    private fun saveDataToCache(photoData: List<Photo>){
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Photo::class.java)
        val adapter: JsonAdapter<List<Photo>> = moshi.adapter(listType)
        val json = adapter.toJson((photoData))
        fileHelper.saveTextToFile(app, json)
    }

    private fun readDataFromCache(): List<Photo>{
        val json = fileHelper.readTextFile(app)
        if (json==null){
            return emptyList()
        }
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Photo::class.java)
        val adapter: JsonAdapter<List<Photo>> = moshi.adapter(listType)
        return adapter.fromJson(json) ?: emptyList()
    }
}