package com.example.oblig3.data

import com.example.oblig3.network.ArtApiService

interface ArtPhotosRepository {
    suspend fun getArtPhotos(): List<Photo>
    suspend fun getCategories(): List<Category>
    suspend fun getArtists(): List<Artist>
    suspend fun getFrametypes(): List<Frametype>
    suspend fun getPhotosizes(): List<Photosize>

    suspend fun getPhotosByCategory(categoryId: String): List<Photo>
    suspend fun getPhotosByArtist(artistId: String): List<Photo>
    suspend fun getPhotoById(photoId: String): List<Photo>
    suspend fun getArtistById(artistId: String): List<Artist>
}

class NetworkArtPhotosRepository(
    private val artApiService: ArtApiService
): ArtPhotosRepository {
    override suspend fun getArtPhotos(): List<Photo> =
        artApiService.getPhotos()

    override suspend fun getCategories(): List<Category> =
        artApiService.getCategories()

    override suspend fun getArtists(): List<Artist> =
        artApiService.getArtists()

    override suspend fun getFrametypes(): List<Frametype> =
        artApiService.getFrametypes()

    override suspend fun getPhotosizes(): List<Photosize> =
        artApiService.getPhotosizes()

    override suspend fun getPhotosByCategory(categoryId: String): List<Photo> =
        artApiService.getPhotosByCategory(categoryId)

    override suspend fun getPhotosByArtist(artistId: String): List<Photo> =
        artApiService.getPhotosByArtist(artistId)

    override suspend fun getPhotoById(photoId: String): List<Photo> =
        artApiService.getPhotoById(photoId)

    override suspend fun getArtistById(artistId: String): List<Artist> =
        artApiService.getArtistById(artistId)
}