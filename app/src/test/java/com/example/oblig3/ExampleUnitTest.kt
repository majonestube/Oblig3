package com.example.oblig3

import com.example.oblig3.data.DataSource
import com.example.oblig3.data.FrameSize
import com.example.oblig3.data.FrameType
import com.example.oblig3.data.Photo
import com.example.oblig3.data.PhotoSize
import com.example.oblig3.data.SelectedPhoto
import com.example.oblig3.ui.ArtViewModel
import org.junit.Test

import org.junit.Assert.*
import kotlin.math.roundToInt

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun default_photo_price_test() {
        val viewModel = ArtViewModel()

        val photo1 = DataSource.PhotosForSale[0]
        val photo2 = DataSource.PhotosForSale[1]
        val photo3 = DataSource.PhotosForSale[2]
        val photoSize = PhotoSize.entries[0]
        val frameType = FrameType.entries[0]
        val frameSize = FrameSize.entries[0].size

        viewModel.setPhotoSizeOption(photoSize)
        viewModel.setFrameMaterialOption(frameType)
        viewModel.setFrameSizeOption(frameSize)

        //Testing 3 different photos
        viewModel.setPhoto(photo1)
        val price1 = viewModel.calculatePrice()
        viewModel.setPhoto(photo2)
        val price2 = viewModel.calculatePrice()
        viewModel.setPhoto(photo3)
        val price3 = viewModel.calculatePrice()

        assertEquals(500f,price1)
        assertEquals(600f,price2)
        assertEquals(200f,price3)
    }

    @Test
    fun photo_price_with_extra_price_test() {
        val viewModel = ArtViewModel()

        val photo = DataSource.PhotosForSale[0]
        viewModel.setPhoto(photo)

        //Metal Frame, Medium size photo and medium size frame
        var photoSize = PhotoSize.entries[1]
        var frameType = FrameType.entries[1]
        var frameSize = FrameSize.entries[1].size
        viewModel.setPhotoSizeOption(photoSize)
        viewModel.setFrameMaterialOption(frameType)
        viewModel.setFrameSizeOption(frameSize)
        val price1 = viewModel.calculatePrice()

        //Plastic Frame, Large size photo and large size frame
        photoSize = PhotoSize.entries[2]
        frameType = FrameType.entries[2]
        frameSize = FrameSize.entries[2].size
        viewModel.setPhotoSizeOption(photoSize)
        viewModel.setFrameMaterialOption(frameType)
        viewModel.setFrameSizeOption(frameSize)
        val price2 = viewModel.calculatePrice()

        //Wood Frame, Medium size photo and large size frame
        photoSize = PhotoSize.entries[0]
        frameType = FrameType.entries[1]
        frameSize = FrameSize.entries[2].size
        viewModel.setPhotoSizeOption(photoSize)
        viewModel.setFrameMaterialOption(frameType)
        viewModel.setFrameSizeOption(frameSize)
        val price3 = viewModel.calculatePrice()

        assertEquals(860f,price1)
        assertEquals(990f,price2)
        assertEquals(830f,price3)
    }

    @Test
    fun total_price_test() {
        val viewModel = ArtViewModel()
        val uiState = viewModel.uiState
        val totalPriceAtCreation = uiState.value.totalPrice

        viewModel.setPhoto(DataSource.PhotosForSale[0])
        viewModel.setFrameMaterialOption(FrameType.entries[0])
        viewModel.setFrameSizeOption(FrameSize.entries[0].size)
        viewModel.setPhotoSizeOption(PhotoSize.entries[0])
        viewModel.addPhoto(SelectedPhoto(
            photoId = uiState.value.chosenPhoto.id,
            frameType = uiState.value.chosenFrameMaterial,
            frameWidth = uiState.value.chosenFrameSize,
            photoSize = uiState.value.chosenPhotoSize,
            photoPrice = viewModel.calculatePrice()
        ))

        viewModel.setPhoto(DataSource.PhotosForSale[1])
        viewModel.setFrameMaterialOption(FrameType.entries[1])
        viewModel.setFrameSizeOption(FrameSize.entries[1].size)
        viewModel.setPhotoSizeOption(PhotoSize.entries[1])
        viewModel.addPhoto(SelectedPhoto(
            photoId = uiState.value.chosenPhoto.id,
            frameType = uiState.value.chosenFrameMaterial,
            frameWidth = uiState.value.chosenFrameSize,
            photoSize = uiState.value.chosenPhotoSize,
            photoPrice = viewModel.calculatePrice()
        ))

        viewModel.setPhoto(DataSource.PhotosForSale[2])
        viewModel.setFrameMaterialOption(FrameType.entries[2])
        viewModel.setFrameSizeOption(FrameSize.entries[2].size)
        viewModel.setPhotoSizeOption(PhotoSize.entries[2])
        viewModel.addPhoto(SelectedPhoto(
            photoId = uiState.value.chosenPhoto.id,
            frameType = uiState.value.chosenFrameMaterial,
            frameWidth = uiState.value.chosenFrameSize,
            photoSize = uiState.value.chosenPhotoSize,
            photoPrice = viewModel.calculatePrice()
        ))

        viewModel.setPhoto(DataSource.PhotosForSale[3])
        viewModel.setFrameMaterialOption(FrameType.entries[0])
        viewModel.setFrameSizeOption(FrameSize.entries[1].size)
        viewModel.setPhotoSizeOption(PhotoSize.entries[2])
        viewModel.addPhoto(SelectedPhoto(
            photoId = uiState.value.chosenPhoto.id,
            frameType = uiState.value.chosenFrameMaterial,
            frameWidth = uiState.value.chosenFrameSize,
            photoSize = uiState.value.chosenPhotoSize,
            photoPrice = viewModel.calculatePrice()
        ))
        val totalPrice = viewModel.uiState.value.totalPrice

        viewModel.deletePhoto(uiState.value.picturesChosen[0])
        val totalPriceAfterDelete = viewModel.uiState.value.totalPrice

        while (uiState.value.picturesChosen.isNotEmpty()) {
            viewModel.deletePhoto(uiState.value.picturesChosen[0])
        }
        val totalPriceEmptyList = viewModel.uiState.value.totalPrice

        assertEquals(0,totalPriceAtCreation)
        assertEquals(3410,totalPrice)
        assertEquals(2910,totalPriceAfterDelete)
        assertEquals(0,totalPriceEmptyList)
    }
}