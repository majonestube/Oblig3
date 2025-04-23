package com.example.oblig3.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ArtPhoto (
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)