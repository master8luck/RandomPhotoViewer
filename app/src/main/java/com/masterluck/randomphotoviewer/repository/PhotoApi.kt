package com.masterluck.randomphotoviewer.repository

import com.masterluck.randomphotoviewer.repository.model.PhotoDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

    @GET("photos/?client_id=ab3411e4ac868c2646c0ed488dfd919ef612b04c264f3374c97fff98ed253dc9")
    fun getPhotos(@Query("page") page: Int): Single<List<PhotoDto>>

}