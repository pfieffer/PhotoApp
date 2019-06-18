package com.example.photoapp.model

import java.io.Serializable

data class Photo(val id: String,
                 val likes: Int,
                 val favourites: Int,
                 val tags: String,
                 val previewUrl: String,
                 val webFormatUrl: String) : Serializable {
}