package com.masterluck.randomphotoviewer

data class PhotoDao(
    val id: String,
    val description: String,
    val username: String,
    val rawUrl: String,
    val thumbUrl: String,
)