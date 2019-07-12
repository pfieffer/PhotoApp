package com.example.photoapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photoapp.data.PhotoRepository
import com.example.photoapp.model.PhotoList

class PhotosViewModel : ViewModel() {
    private val mutableLiveData: MutableLiveData<PhotoList>
    private val photosRepository: PhotoRepository = PhotoRepository.instance

    init {
        mutableLiveData = photosRepository.getPhotos()
    }

    fun getPhotosRepository(): MutableLiveData<PhotoList> {
        return mutableLiveData
    }
}