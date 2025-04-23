package com.example.oblig3.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ArtPhoto (
    val id: String,
    val title: String,
    val imageThumbUrl: String,
    @SerialName(value = "imageUrl")
    val imgSrc: String,
    val artistId: String,
    val categoryId: String,
    val price: String
)