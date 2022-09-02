package com.masterluck.randomphotoviewer.repository.model

data class PhotoDto(
    val id: String,
    val description: String?,
    val user: UserDto,
    val urls: UrlDto,
)
