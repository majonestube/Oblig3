package com.example.oblig3.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo (
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
data class Artist(
    val id: String,
    val firstName: String,
    val lastName: String
)

@Entity(tableName = "selectedPhotos")
data class SelectedPhoto (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val photoId: String,
    val artistId: String,
    val photoTitle: String,
    val frameType: String,
    val frameWidth: Int,
    val photoSize: String,
    val photoPrice: Double = 0.0,
)


@Serializable
data class Category (
    val id: String,
    val name: String
)

@Serializable
data class Frametype (
    val id: String,
    val name: String,
    val color: String,
    val extraPrice: Double
)

@Serializable
data class Photosize (
    val id: String,
    val name: String,
    val size: Int,
    val extraPrice: Double
)

enum class FrameSize(val extraPrice: Float, val size: Int = 170) {
    SMALL(0f, size=10),
    MEDIUM(130f, size=15),
    LARGE(230f, size=20)
}

val defaultPhoto: Photo = Photo(
    id = "1",
    title = "",
    imageThumbUrl = "",
    imgSrc = "",
    artistId = "",
    categoryId = "",
    price = 1.0
)
val defaultCategory: Category = Category(
    id = "1",
    name = "Default Category"
)

val defaultArtFrametype: Frametype = Frametype(
    id = "0",
    name = "",
    color = "0xFFffff",
    extraPrice = 0.0
)
val defaultPhotoSize: Photosize = Photosize(
    id = "",
    name = "",
    size = 0,
    extraPrice = 0.0
)