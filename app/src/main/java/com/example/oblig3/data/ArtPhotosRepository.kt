package com.example.oblig3.data

import com.example.oblig3.network.ArtApiService
//import com.example.oblig3.network.ArtArtist
import com.example.oblig3.network.ArtCategory
import com.example.oblig3.network.ArtFrametype
import com.example.oblig3.network.ArtPhoto
import com.example.oblig3.network.ArtPhotosize

interface ArtPhotosRepository {
    suspend fun getArtPhotos(): List<ArtPhoto>
    suspend fun getCategories(): List<Category>
    suspend fun getArtists(): List<Artist>
    suspend fun getFrametypes(): List<ArtFrametype>
    suspend fun getPhotosizes(): List<ArtPhotosize>

    suspend fun getPhotosByCategory(categoryId: String): List<ArtPhoto>
    suspend fun getPhotosByArtist(artistId: String): List<ArtPhoto>
    suspend fun getPhotoById(photoId: String): List<ArtPhoto>
    suspend fun getArtistById(artistId: String): List<Artist>
}

class NetworkArtPhotosRepository(
    private val artApiService: ArtApiService
): ArtPhotosRepository {
    override suspend fun getArtPhotos(): List<ArtPhoto> =
        artApiService.getPhotos()

    override suspend fun getCategories(): List<Category> =
        artApiService.getCategories()

    override suspend fun getArtists(): List<Artist> =
        artApiService.getArtists()

    override suspend fun getFrametypes(): List<ArtFrametype> =
        artApiService.getFrametypes()

    override suspend fun getPhotosizes(): List<ArtPhotosize> =
        artApiService.getPhotosizes()

    override suspend fun getPhotosByCategory(categoryId: String): List<ArtPhoto> =
        artApiService.getPhotosByCategory(categoryId)

    override suspend fun getPhotosByArtist(artistId: String): List<ArtPhoto> =
        artApiService.getPhotosByArtist(artistId)

    override suspend fun getPhotoById(photoId: String): List<ArtPhoto> =
        artApiService.getPhotoById(photoId)

    override suspend fun getArtistById(artistId: String): List<Artist> =
        artApiService.getArtistById(artistId)
}