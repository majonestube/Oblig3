package com.example.oblig3.data

import com.example.oblig3.ui.ArtScreen

data class ArtUiState(
    val picturesChosen: List<SelectedPhoto> = listOf(),
    val chosenArtist: Long = 0,
    val chosenCategory: Category = Category.NATURE
)
