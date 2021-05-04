package com.example.androiddata.data

// gets the entire photo object

data class Photo (
    val id: String,
    val alt_description: String,
    val description: String,
    val likes: String,
    val urls: ImageUrls,
    val user: ImageUser,
)