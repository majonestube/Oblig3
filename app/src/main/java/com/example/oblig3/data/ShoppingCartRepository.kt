package com.example.oblig3.data

import kotlinx.coroutines.flow.Flow

class ShoppingCartRepository(private val selectedPhotoDao: SelectedPhotoDao) {
    fun getAllSelectedPhotosStream(): Flow<List<SelectedPhoto>> {
        return selectedPhotoDao.getAllSelectedPhotos()
    }

    suspend fun insertSelectedPhoto(selectedPhoto: SelectedPhoto) {
        selectedPhotoDao.insert(selectedPhoto)
    }

    suspend fun deleteSelectedPhoto(selectedPhoto: SelectedPhoto) {
        selectedPhotoDao.delete(selectedPhoto)
    }

    suspend fun getTotalPhotoPrice(): Flow<Double?> {
        return selectedPhotoDao.getTotalPhotoPrice()
    }
}