package com.example.oblig3.data

//import com.example.oblig3.network.ArtArtist
import com.example.oblig3.data.DataSource.defaultCategory
import com.example.oblig3.data.DataSource.defaultPhoto
import com.example.oblig3.network.ArtCategory
import com.example.oblig3.network.ArtFrametype
import com.example.oblig3.network.ArtPhoto
import com.example.oblig3.network.ArtPhotosize

data class ArtUiState(
    val allPhotos: List<ArtPhoto> = listOf(),
    val categories: List<Category> = listOf(),
    val artists: List<Artist> = listOf(),
    val frameTypes: List<ArtFrametype> = listOf(),
    val photosize: List<ArtPhotosize> = listOf(),
    val listOfPhotos: List<ArtPhoto> = listOf(),
    val photoById: ArtPhoto = defaultPhoto,
    val artistById: Artist = Artist("1","",""),

    val picturesChosen: List<SelectedPhoto> = listOf(),
    val chosenArtist: String = "0",
    val chosenCategory: Category = defaultCategory,
    val chosenPhoto: ArtPhoto = DataSource.defaultPhoto,
    val chosenFrameMaterial: FrameType = FrameType.WOOD,
    val chosenPhotoSize: PhotoSize = PhotoSize.SMALL,
    val chosenFrameSize: Int = FrameSize.SMALL.size,
    val totalPrice: Int = 0
)
