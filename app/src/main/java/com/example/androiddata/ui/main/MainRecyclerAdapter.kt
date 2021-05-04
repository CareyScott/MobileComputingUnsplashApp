package com.example.androiddata.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androiddata.R
import com.example.androiddata.data.Photo
import com.example.androiddata.data.PhotoResult

class MainRecyclerAdapter  (val context: Context,
                            val photos: List<Photo>,
//                            val photosList: List<PhotoResult>,
                            val itemListener: PhotoItemListener
                          ):
RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>()

{
    inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView){
                val likesText = itemView.findViewById<TextView>(R.id.likesText)
                val small = itemView.findViewById<ImageView>(R.id.small)
//                val alt_descriptionText = itemView.findViewById<TextView>(R.id.alt_descriptionText)

            }

    interface PhotoItemListener{
        fun onPhotoItemClick(photo: Photo)
    }
    override fun getItemCount() = photos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
val inflater = LayoutInflater.from(parent.context)
    val view = inflater.inflate(R.layout.photo_grid_item, parent, false)
    return ViewHolder(view)}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val photo = photos[position]
        with(holder) {
            likesText?.let {
                it.text = photo.likes
                it.contentDescription = photo.likes
            }
            Glide.with(context)
                .load(photo.urls.small)
                .into(small)
            holder.small.setOnClickListener{
                itemListener.onPhotoItemClick(photo)
            }



        }
    }



}