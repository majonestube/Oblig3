package com.example.oblig3.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SelectedPhotoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(selectedPhoto: SelectedPhoto)

    @Delete
    suspend fun delete(selectedPhoto: SelectedPhoto)

    @Query("SELECT * FROM selectedPhotos")
    fun getAllSelectedPhotos(): Flow<List<SelectedPhoto>>
}