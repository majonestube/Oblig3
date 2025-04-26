package com.example.oblig3.network


import com.example.oblig3.data.Artist
import com.example.oblig3.data.Category
import com.example.oblig3.data.Frametype
import com.example.oblig3.data.Photo
import com.example.oblig3.data.Photosize
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtApiService {
    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("artists")
    suspend fun getArtists(): List<Artist>

    @GET("photos")
    suspend fun getPhotos(): List<Photo>

    @GET("frametypes")
    suspend fun getFrametypes(): List<Frametype>

    @GET("photosizes")
    suspend fun getPhotosizes(): List<Photosize>

    @GET("artists")
    suspend fun getArtistById(
        @Query("id") photoId: String
    ): List<Artist>

    @GET("photos")
    suspend fun getPhotoById(
        @Query("id") photoId: String
    ): List<Photo>

    @GET("photos")
    suspend fun getPhotosByCategory(
        @Query("categoryId" ) categoryId: String
    ): List<Photo>

    @GET("photos")
    suspend fun getPhotosByArtist(
        @Query("artistId" ) artistId: String
    ): List<Photo>


}