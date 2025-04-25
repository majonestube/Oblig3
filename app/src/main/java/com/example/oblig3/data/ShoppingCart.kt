package com.example.oblig3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SelectedPhoto::class], version = 3, exportSchema = false)
abstract class ShoppingCart: RoomDatabase() {
    abstract fun selectedPhotoDao(): SelectedPhotoDao

    companion object {
        @Volatile
        private var Instance: ShoppingCart? = null

        fun getShoppingCart(context: Context): ShoppingCart {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ShoppingCart::class.java, "shopping_cart")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}