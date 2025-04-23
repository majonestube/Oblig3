package com.example.oblig3.network


import retrofit2.http.GET

interface ArtApiService {
    @GET("photos")
    suspend fun getPhotos(): List<ArtPhoto>
}