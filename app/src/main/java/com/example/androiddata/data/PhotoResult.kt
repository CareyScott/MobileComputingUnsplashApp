package com.example.androiddata.data

// for search functionality -- the resulted photo appears here
data class PhotoResult (
    val id: String,
    val alt_description: String,
    val description: String,
    val likes: String,
    val urls: ImageUrls,
    val user: ImageUser,
)
