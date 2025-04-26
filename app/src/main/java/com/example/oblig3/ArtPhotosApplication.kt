package com.example.oblig3

import android.app.Application

import com.example.oblig3.data.AppContainer
import com.example.oblig3.data.DefaultAppContainer

class ArtPhotosApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}