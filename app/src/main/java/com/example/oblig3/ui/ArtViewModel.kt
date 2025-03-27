package com.example.oblig3.ui

import androidx.lifecycle.ViewModel
import com.example.oblig3.data.ArtUiState
import com.example.oblig3.data.Category
import com.example.oblig3.data.FrameType
import com.example.oblig3.data.Photo
import com.example.oblig3.data.PhotoSize
import com.example.oblig3.data.SelectedPhoto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val EXTRA_PRICE = 200

class ArtViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ArtUiState())
    val uiState: StateFlow<ArtUiState> = _uiState.asStateFlow()

    fun addPhoto(
        photo: SelectedPhoto) {
        _uiState.update { currentState ->
            currentState.copy(
                picturesChosen = currentState.picturesChosen + photo
            )
        }
    }

    fun deletePhoto(
        photo: SelectedPhoto
    ) {
        _uiState.update { currentScreen ->
            currentScreen.copy(
                picturesChosen = currentScreen.picturesChosen - photo
            )
        }
    }

    // Set the selected artist
    fun setArtist(artistId: Long) {
        _uiState.update { currentState ->
            currentState.copy(
                chosenArtist = artistId
            )
        }
    }

    // Set the selected category
    fun setCategory(categoryId: Category) {
        _uiState.update { currentState ->
            currentState.copy(
                chosenCategory = categoryId
            )
        }
    }

    fun setPhoto(photo: Photo) {
        _uiState.update { currentState ->
            currentState.copy(
                chosenPhoto = photo
            )
        }
    }

    fun setFrameMaterialOption(option: FrameType) {
        _uiState.update { currentState ->
            currentState.copy(
                chosenFrameMaterial = option
            )
        }
    }

    fun setFrameSizeOption(option: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                chosenFrameSize = option
            )
        }
    }

    fun setPhotoSizeOption(option: PhotoSize) {
        _uiState.update { currentState ->
            currentState.copy(
                chosenPhotoSize = option
            )
        }
    }

    fun reset() {
        _uiState.value = ArtUiState()
    }

}