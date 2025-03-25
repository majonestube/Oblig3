package com.example.oblig3.data

data class ArtUiState(
    val pictures: List<Photo> = listOf(),
    val frames: List<FrameType> = listOf(),
    val price: String = ""
)
