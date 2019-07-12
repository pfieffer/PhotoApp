package com.example.photoapp.data

import androidx.lifecycle.MutableLiveData
import com.example.photoapp.data.api.PhotoAPI
import com.example.photoapp.data.api.RetrofitService
import com.example.photoapp.model.PhotoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoRepository {
    private val photoApi: PhotoAPI = RetrofitService.createService(PhotoAPI::class.java)

    fun getPhotos(): MutableLiveData<PhotoList> {
        val photosData = MutableLiveData<PhotoList>()
        photoApi.getPhotos().enqueue(object : Callback<PhotoList> {
            override fun onResponse(
                call: Call<PhotoList>,
                response: Response<PhotoList>
            ) {
                if (response.isSuccessful) {
                    photosData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<PhotoList>, t: Throwable) {
                photosData.value = null
            }
        })
        return photosData
    }

    companion object {

        private var photoRepository: PhotoRepository? = null

        val instance: PhotoRepository
            get() {
                if (photoRepository == null) {
                    photoRepository = PhotoRepository()
                }
                return photoRepository as PhotoRepository
            }
    }
}