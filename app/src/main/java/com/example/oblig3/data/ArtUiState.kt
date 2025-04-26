package com.example.oblig3.data


data class ArtUiState(
    val allPhotos: List<Photo> = listOf(),
    val categories: List<Category> = listOf(),
    val artists: List<Artist> = listOf(),
    val frameTypes: List<Frametype> = listOf(),
    val photosize: List<Photosize> = listOf(),
    val listOfPhotos: List<Photo> = listOf(),
    val photoById: Photo = defaultPhoto,
    val artistById: Artist = Artist("1","",""),

    val chosenArtist: String = "0",
    val chosenCategory: Category = defaultCategory,
    val chosenPhoto: Photo = defaultPhoto,
    val chosenFrameMaterial: Frametype = defaultArtFrametype,
    val chosenPhotoSize: Photosize = defaultPhotoSize,
    val chosenFrameSize: Int = FrameSize.SMALL.size,
    val totalPrice: Double = 0.0
)
