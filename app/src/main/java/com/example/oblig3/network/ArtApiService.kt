package com.example.oblig3.network


import retrofit2.http.GET
import retrofit2.http.Query

interface ArtApiService {
    @GET("categories")
    suspend fun getCategories(): List<ArtCategory>

    @GET("artists")
    suspend fun getArtists(): List<ArtArtist>

    @GET("photos")
    suspend fun getPhotos(): List<ArtPhoto>

    @GET("frametypes")
    suspend fun getFrametypes(): List<ArtFrametype>

    @GET("photosizes")
    suspend fun getPhotosizes(): List<ArtPhotosize>

    @GET("photos")
    suspend fun getPhotosByCategory(
        @Query("categoryId" ) categoryId: String
    ): List<ArtPhoto>
}