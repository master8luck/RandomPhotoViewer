package com.masterluck.randomphotoviewer.pictureslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.masterluck.randomphotoviewer.PhotoDao
import com.masterluck.randomphotoviewer.repository.PhotoMapper
import com.masterluck.randomphotoviewer.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class PicturesListViewModel: ViewModel() {

    private val photosListLiveData = MutableLiveData<List<PhotoDao>>()
    private val photosList = mutableListOf<PhotoDao>()
    val photos = photosListLiveData as LiveData<List<PhotoDao>>

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading as LiveData<Boolean>

    private var page = 1

    init {
        fetchMorePhoto()
    }

    fun fetchMorePhoto() {
        _isLoading.postValue(true)
        Repository.api.getPhotos(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ photos ->

                photos.forEach { photoDto ->
                    val photoDao = PhotoMapper.mapPhotoDtoToPhotoDao(photoDto)
                    photosList.add(photoDao)
                }

                photosListLiveData.postValue(photosList.toList())
                _isLoading.postValue(false)

                page++

            }, {
                photosListLiveData.postValue(listOf())
                _isLoading.postValue(false)
            })
    }

}