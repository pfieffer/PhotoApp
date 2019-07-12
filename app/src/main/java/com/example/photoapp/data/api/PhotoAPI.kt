package com.example.photoapp.data.api


import com.example.photoapp.model.PhotoList
import retrofit2.Call
import retrofit2.http.GET

interface PhotoAPI {
    @GET("?key=12806732-4f371ba6a1ad945965886e8ca&q=nature&image_type=photo")
    fun getPhotos(): Call<PhotoList>
}