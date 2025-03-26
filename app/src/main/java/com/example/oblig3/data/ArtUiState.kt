package com.example.oblig3.data

import com.example.oblig3.ui.ArtScreen

data class ArtUiState(
    val picturesChosen: List<Photo> = listOf(),
    val framesChosen: List<FrameType> = listOf(),
    val numberOfPicturesChosen: Int = 0,
    val price: String = "",
    val chosenArtist: Long = 0,
    val chosenCategory: Category = Category.NATURE
)
