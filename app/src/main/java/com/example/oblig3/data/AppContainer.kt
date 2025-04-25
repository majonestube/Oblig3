package com.example.oblig3.data

import android.content.Context
import com.example.oblig3.network.ArtApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val artPhotosRepository: ArtPhotosRepository
    val shoppingCartRepository: ShoppingCartRepository
}

class DefaultAppContainer(context: Context): AppContainer {
    private val baseUrl = "http://10.0.2.2:3000/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: ArtApiService by lazy {
        retrofit.create(ArtApiService::class.java)
    }

    override val artPhotosRepository: ArtPhotosRepository by lazy {
        NetworkArtPhotosRepository(retrofitService)
    }

    private val shoppingCart: ShoppingCart by lazy {
        ShoppingCart.getShoppingCart(context.applicationContext)
    }

    override val shoppingCartRepository: ShoppingCartRepository by lazy {
        ShoppingCartRepository(shoppingCart.selectedPhotoDao())
    }

}