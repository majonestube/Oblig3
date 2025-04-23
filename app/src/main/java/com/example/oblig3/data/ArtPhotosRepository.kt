package com.example.oblig3.data

import com.example.oblig3.network.ArtApiService
import com.example.oblig3.network.ArtCategory
import com.example.oblig3.network.ArtPhoto

interface ArtPhotosRepository {
    suspend fun getArtPhotos(): List<ArtPhoto>
    suspend fun getCategories(): List<ArtCategory>
}

class NetworkArtPhotosRepository(
    private val artApiService: ArtApiService
): ArtPhotosRepository {
    override suspend fun getArtPhotos(): List<ArtPhoto> =
        artApiService.getPhotos()

    override suspend fun getCategories(): List<ArtCategory> =
        artApiService.getCategories()
}