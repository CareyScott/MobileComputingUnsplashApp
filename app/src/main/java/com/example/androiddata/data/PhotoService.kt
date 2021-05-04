package com.example.androiddata.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.ResponseCache

// api methods go here
interface PhotoService {
    //gets photos from this link
    @GET("/photos?client_id=99s9eFcJIH_mgs5cHe7nCvdAk2z9wkf5uXxkbo9S83k")
   suspend fun getPhotoData(): Response<List<Photo>>

   @GET("/search/photos?query=school&client_id=99s9eFcJIH_mgs5cHe7nCvdAk2z9wkf5uXxkbo9S83k")
   suspend fun simpleSearchPhotos(
       @Query("q") searchString: String
   ): PhotosResponse
}