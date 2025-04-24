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
    val price: Double
)

@Serializable
data class ArtCategory (
    val id: String,
    val name: String
)

//data class Artist moved to "artClasses.kt"

@Serializable
data class ArtFrametype (
    val id: String,
    val name: String,
    val color: String,
    val extraPrice: Double
)

@Serializable
data class ArtPhotosize (
    val id: String,
    val name: String,
    val size: Int,
    val extraPrice: Double
)