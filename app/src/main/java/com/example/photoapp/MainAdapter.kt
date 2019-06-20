package com.example.photoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photoapp.model.Photo

class MainAdapter(var photos: List<Photo>, var clickListener: View.OnClickListener) :
    RecyclerView.Adapter<MainAdapter.PhotoViewHolder>() {

    override fun onBindViewHolder(photoViewHodler: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        photoViewHodler.tags.text = photo.tags
        photoViewHodler.likes.text = photo.likes.toString()
        photoViewHodler.favorites.text = photo.favorites.toString()
        if (photo.previewURL.isNotEmpty()) {
            Glide.with(photoViewHodler.tags.context)
                .load(photo.previewURL)
                .into(photoViewHodler.photo_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_photo, parent, false))
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    fun getPhoto(adapterPosition: Int): Photo {
        return photos[adapterPosition]
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tags: TextView
        var likes: TextView
        var favorites: TextView
        var photo_item: ImageView

        init {
            if (clickListener != null) {
                itemView.setOnClickListener(clickListener)
            }
            itemView.tag = this
            tags = itemView.findViewById(R.id.tags) as TextView
            likes = itemView.findViewById(R.id.likes) as TextView
            favorites = itemView.findViewById(R.id.favorites) as TextView
            photo_item = itemView.findViewById(R.id.photo_item) as ImageView
        }
    }
}