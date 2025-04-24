package com.example.oblig3.data

import com.example.oblig3.network.ArtArtist
import com.example.oblig3.network.ArtCategory
import com.example.oblig3.network.ArtFrametype
import com.example.oblig3.network.ArtPhoto
import com.example.oblig3.network.ArtPhotosize

data class ArtUiState(
    val allPhotos: List<ArtPhoto> = listOf(),
    val categories: List<ArtCategory> = listOf(),
    val artists: List<ArtArtist> = listOf(),
    val frameTypes: List<ArtFrametype> = listOf(),
    val photosize: List<ArtPhotosize> = listOf(),
    val listOfPhotos: List<ArtPhoto> = listOf(),

    val picturesChosen: List<SelectedPhoto> = listOf(),
    val chosenArtist: Long = 0,
    val chosenCategory: Category = Category.NATURE,
    val chosenPhoto: Photo = DataSource.PhotosForSale[0],
    val chosenFrameMaterial: FrameType = FrameType.WOOD,
    val chosenPhotoSize: PhotoSize = PhotoSize.SMALL,
    val chosenFrameSize: Int = FrameSize.SMALL.size,
    val totalPrice: Int = 0
)
