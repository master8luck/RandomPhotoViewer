package com.masterluck.randomphotoviewer.repository

import com.masterluck.randomphotoviewer.PhotoDao
import com.masterluck.randomphotoviewer.repository.model.PhotoDto

object PhotoMapper {
    fun mapPhotoDtoToPhotoDao(photoDto: PhotoDto) : PhotoDao {
        return PhotoDao(
            photoDto.id,
            photoDto.description ?: "",
            photoDto.user.username ?: "",
            photoDto.urls.raw,
            photoDto.urls.thumb,
        )
    }
}