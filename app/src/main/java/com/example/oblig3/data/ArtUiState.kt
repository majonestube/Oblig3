package com.example.oblig3.data

data class ArtUiState(
    val picturesChosen: List<SelectedPhoto> = listOf(),
    val chosenArtist: Long = 0,
    val chosenCategory: Category = Category.NATURE,
    val chosenPhoto: Photo = DataSource.PhotosForSale[0],
    val chosenFrameMaterial: FrameType = FrameType.WOOD,
    val chosenPhotoSize: PhotoSize = PhotoSize.SMALL,
    val chosenFrameSize: Int = FrameSize.SMALL.size,
    val totalPrice: Int = 0
)
