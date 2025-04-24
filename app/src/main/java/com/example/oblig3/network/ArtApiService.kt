package com.example.oblig3.network


import com.example.oblig3.data.Artist
import com.example.oblig3.data.Category
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtApiService {
    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("artists")
    suspend fun getArtists(): List<Artist>

    @GET("photos")
    suspend fun getPhotos(): List<ArtPhoto>

    @GET("frametypes")
    suspend fun getFrametypes(): List<ArtFrametype>

    @GET("photosizes")
    suspend fun getPhotosizes(): List<ArtPhotosize>

    @GET("artists")
    suspend fun getArtistById(
        @Query("id") photoId: String
    ): List<Artist>

    @GET("photos")
    suspend fun getPhotoById(
        @Query("id") photoId: String
    ): List<ArtPhoto>

    @GET("photos")
    suspend fun getPhotosByCategory(
        @Query("categoryId" ) categoryId: String
    ): List<ArtPhoto>

    @GET("photos")
    suspend fun getPhotosByArtist(
        @Query("artistId" ) artistId: String
    ): List<ArtPhoto>


}