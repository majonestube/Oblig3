package com.example.oblig3.data

import com.example.oblig3.R

object DataSource {
    val Artists: List<Artist> = listOf(
        Artist(
            id = 1,
            name = "Martine",
            familyName = "Vold"
        ),
        Artist(
            id = 2,
            name = "John",
            familyName = "Smith"
        ),
        Artist(
            id = 3,
            name = "Tomas",
            familyName = "Horvli"
        ),
        Artist(
            id = 4,
            name = "Lisa",
            familyName = "Tønne"
        ),
    )

    val PhotosForSale: List<Photo> = listOf(
        // Naturfotoer
        Photo(
            id = 1,
            title = "Wildflowers",
            imageResId = R.drawable.nature_1,
            artistId = 1,
            category = Category.NATURE,
            price = 0.5f
        )
    )

}