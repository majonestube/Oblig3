package com.example.oblig3.data

import com.example.oblig3.network.ArtApiService
import com.example.oblig3.network.ArtPhoto

interface ArtPhotosRepository {
    suspend fun getArtPhotos(): List<ArtPhoto>
}

class NetworkArtPhotosRepository(
    private val artApiService: ArtApiService
): ArtPhotosRepository {
    override suspend fun getArtPhotos(): List<ArtPhoto> =
        artApiService.getPhotos()
}