package com.example.oblig3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.oblig3.ArtPhotosApplication
import com.example.oblig3.data.ArtPhotosRepository
import com.example.oblig3.data.ArtUiState
import com.example.oblig3.data.Category
import com.example.oblig3.data.DataSource
import com.example.oblig3.data.FrameSize
import com.example.oblig3.data.FrameType
import com.example.oblig3.data.PhotoSize
import com.example.oblig3.data.SelectedPhoto
import com.example.oblig3.data.ShoppingCartRepository
import com.example.oblig3.network.ArtPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val EXTRA_PRICE = 200

class ArtViewModel(
    private val artPhotosRepository: ArtPhotosRepository,
    private val shoppingCartRepository: ShoppingCartRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(ArtUiState())
    val uiState: StateFlow<ArtUiState> = _uiState.asStateFlow()

    fun getAllSelectedPhotos(): Flow<List<SelectedPhoto>> {
        return shoppingCartRepository.getAllSelectedPhotosStream()
    }

    fun getPhotoById(photoId: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    photoById = artPhotosRepository.getPhotoById(photoId = photoId)[0]
                )
            }
        }
    }

    fun getArtistById(artistId: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    artistById = artPhotosRepository.getArtistById(artistId = artistId)[0]
                )
            }
        }
    }

    fun getAllPhotos() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    allPhotos = artPhotosRepository.getArtPhotos()
                )
            }
        }
    }


    fun getCategories() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    categories = artPhotosRepository.getCategories()
                )
            }
        }
    }

    fun getArtists(){
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    artists = artPhotosRepository.getArtists()
                )
            }
        }
    }

    fun getFrametypes(){
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    frameTypes = artPhotosRepository.getFrametypes()
                )
            }
        }
    }

    fun getPhotosizes(){
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    photosize = artPhotosRepository.getPhotosizes()
                )
            }
        }
    }

    fun getPhotosByCategory(categoryId: String){
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    listOfPhotos = artPhotosRepository.getPhotosByCategory(categoryId)
                )
            }
        }
    }

    fun getPhotosByArtist(artistId: String){
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    listOfPhotos = artPhotosRepository.getPhotosByArtist(artistId)
                )
            }
        }
    }

    fun addPhoto(
        photo: SelectedPhoto) {
        viewModelScope.launch {
            shoppingCartRepository.insertSelectedPhoto(photo)
        }
    }

    fun deletePhoto(
        photo: SelectedPhoto
    ) {
        viewModelScope.launch {
            shoppingCartRepository.deleteSelectedPhoto(photo)
        }
    }



    // Set the selected artist
    fun setArtist(artistId: String) {
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

    fun setPhoto(photo: ArtPhoto) {
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

    fun calculatePrice(): Double {
        var framePrice = 0f
        val photoPrice = uiState.value.chosenPhoto.price
        val photoSizePrice = uiState.value.chosenPhotoSize.extraPrice
        val materialPrice = uiState.value.chosenFrameMaterial.extraPrice

        for (frame in FrameSize.entries) {
            if (frame.size == uiState.value.chosenFrameSize) {
                framePrice = frame.extraPrice
            }
        }

        return framePrice + materialPrice + photoPrice * DataSource.PHOTO_PRICE + photoSizePrice
    }

    /*
    fun setTotalPrice() {
        _uiState.update { currentState ->
            currentState.copy(
                totalPrice = uiState.value.picturesChosen.sumOf { it.photoPrice.toDouble() }.roundToInt()
            )
        }
    }
    */

    fun resetDetails() {
        val frameType = FrameType.entries[0]
        val frameSize = FrameSize.entries[0].size
        val photoSize = PhotoSize.entries[0]

        setFrameMaterialOption(frameType)
        setFrameSizeOption(frameSize)
        setPhotoSizeOption(photoSize)
    }

    fun reset() {
        _uiState.value = ArtUiState()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as? ArtPhotosApplication)
                    ?: throw IllegalStateException("Application is not ArtPhotosApplication")
                val artPhotosRepository = try {
                    application.container.artPhotosRepository
                } catch (e: Exception) {
                    throw IllegalStateException("Failed to get reporitory: ${e.message}")
                }
                val shoppingCartRepository = try {
                    application.container.shoppingCartRepository
                } catch (e: Exception) {
                    throw IllegalStateException("Failed to get reporitory: ${e.message}")
                }
                ArtViewModel(
                    artPhotosRepository = artPhotosRepository,
                    shoppingCartRepository = shoppingCartRepository)
            }
        }
    }

}