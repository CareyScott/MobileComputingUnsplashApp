package com.example.androiddata.data

// gets the users profile photos from the nested user object

data class ImageUserProfilePicture (
    val small : String,
    val large : String,
    val medium : String

)

{
    val profileImage
        get() = small

    val profileImage2
        get() = large

    val profileImage3
        get() = medium

}