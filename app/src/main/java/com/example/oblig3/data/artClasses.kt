package com.example.oblig3.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

data class Photo(
    /** Unik ID til bildet **/
    val id: Long,
    val title: String = "",
    @DrawableRes
    val imageResId: Int,
    val artistId: Long,
    val category: Category,
    val price: Float = 0.0f
)

@Serializable
data class Artist(
    val id: String,
    val firstName: String,
    val lastName: String
)

/*
data class Artist(
    val id: Long,
    val name: String = "",
    val familyName: String = ""
)*/

/*
data class SelectedPhoto(
    val photoId: String,
    val artistId: String,
    val frameType: FrameType,
    val frameWidth: Int,
    val photoSize: PhotoSize,
    val photoPrice: Double = 0.0,
)
*/

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

/*
enum class Category {
    NATURE(),
    FOOD(),
    SPORT()
}
*/
enum class FrameType(val extraPrice: Float, val color: Color = Color.Yellow) {
    WOOD(0f, color = Color.Yellow),
    METAL(100f, color = Color.Blue),
    PLASTIC(30f, color = Color.Green)
}

enum class PhotoSize(val extraPrice: Float, val size: Int = 170) {
    SMALL(0f, size=170),
    MEDIUM(130f, size=200),
    LARGE(230f, size=250)
}

enum class FrameSize(val extraPrice: Float, val size: Int = 170) {
    SMALL(0f, size=10),
    MEDIUM(130f, size=15),
    LARGE(230f, size=20)
}