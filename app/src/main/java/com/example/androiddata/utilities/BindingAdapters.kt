package com.example.androiddata.utilities

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

// binging adapter is used to display the images within the application  from an api link
// creates a useable value for xml imageviews
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String){
    Glide.with(view.context)
        .load(imageUrl)
        .into(view)
}

//@BindingAdapter("user")
//fun loadUser(view: TextView, value: String){
//    val text = "${value}"
//    view.text = text
//}