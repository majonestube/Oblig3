package com.example.oblig3.data

import com.example.oblig3.R

object DataSource {
    const val PHOTO_PRICE = 1000

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

    val Categories: List<Category> = listOf(
        Category.NATURE,
        Category.FOOD,
        Category.SPORT
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
        ),
        Photo(
            id = 2,
            title = "Meadow",
            imageResId = R.drawable.nature_2,
            artistId = 2,
            category = Category.NATURE,
            price = 0.6f
        ),
        Photo(
            id = 3,
            title = "Desert",
            imageResId = R.drawable.nature_3,
            artistId = 3,
            category = Category.NATURE,
            price = 0.2f
        ),
        Photo(
            id = 4,
            title = "Waterfall",
            imageResId = R.drawable.nature_4,
            artistId = 4,
            category = Category.NATURE,
            price = 0.9f
        ),
        Photo(
            id = 5,
            title = "Pizza",
            imageResId = R.drawable.food_1,
            artistId = 1,
            category = Category.FOOD,
            price = 0.5f
        ),
        Photo(
            id = 6,
            title = "Taco",
            imageResId = R.drawable.food_2,
            artistId = 3,
            category = Category.FOOD,
            price = 0.4f
        ),
        Photo(
            id = 7,
            title = "Sandwich",
            imageResId = R.drawable.food_3,
            artistId = 4,
            category = Category.FOOD,
            price = 0.8f
        ),
        Photo(
            id = 8,
            title = "Soccer",
            imageResId = R.drawable.sport_1,
            artistId = 2,
            category = Category.SPORT,
            price = 1f
        ),
        Photo(
            id = 9,
            title = "Climbing color",
            imageResId = R.drawable.sport_2,
            artistId = 1,
            category = Category.SPORT,
            price = 0.75f
        ),
        Photo(
            id = 10,
            title = "Climbing silhouette",
            imageResId = R.drawable.sport_3,
            artistId = 3,
            category = Category.SPORT,
            price = 0.15f
        ),
        Photo(
            id = 11,
            title = "Basketball",
            imageResId = R.drawable.sport_4,
            artistId = 4,
            category = Category.SPORT,
            price = 0.6f
        ),
        Photo(
            id = 12,
            title = "Climbing silhouette",
            imageResId = R.drawable.sport_5,
            artistId = 1,
            category = Category.SPORT,
            price = 0.7f
        ),
        Photo(
            id = 13,
            title = "Cycling",
            imageResId = R.drawable.sport_6,
            artistId = 3,
            category = Category.SPORT,
            price = 0.4f
        ),
    )

    fun photosByArtist(artistId: Long): List<Photo> {
        return PhotosForSale.filter { it.artistId == artistId }
    }

    fun photosByCategory(categoryId: Category): List<Photo> {
        return PhotosForSale.filter { it.category == categoryId }
    }
}