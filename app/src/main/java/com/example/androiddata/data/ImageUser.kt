package com.example.androiddata.data


// gets the users data from the nested user object
data class ImageUser(
    val name: String,
    val total_likes: String,
    val total_photos: String,
    val instagram_username: String,
    val twitter_username: String,
    val links: ImageUserLinks,
    val profile_image: ImageUserProfilePicture

)

//{
//    val ImageUserName
//        get() = name
//}