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
import com.example.oblig3.data.Artist
import com.example.oblig3.data.Category
import com.example.oblig3.data.FrameSize
import com.example.oblig3.data.Frametype
import com.example.oblig3.data.Photo
import com.example.oblig3.data.Photosize
import com.example.oblig3.data.SelectedPhoto
import com.example.oblig3.data.ShoppingCartRepository
import com.example.oblig3.data.defaultArtFrametype
import com.example.oblig3.data.defaultPhoto
import com.example.oblig3.data.defaultPhotoSize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArtViewModel(
    private val artPhotosRepository: ArtPhotosRepository,
    private val shoppingCartRepository: ShoppingCartRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(ArtUiState())
    val uiState: StateFlow<ArtUiState> = _uiState.asStateFlow()


    fun getAllSelectedPhotos(): Flow<List<SelectedPhoto>> {
        return shoppingCartRepository.getAllSelectedPhotosStream()
    }

    private val artistCache = mutableMapOf<String, Artist>()

    suspend fun getArtistCached(id: String): Artist {
        return artistCache[id] ?: artPhotosRepository.getArtistById(id).first().also {
            artistCache[id] = it
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

    fun setPhoto(photo: Photo) {
        _uiState.update { currentState ->
            currentState.copy(
                chosenPhoto = photo
            )
        }
    }

    fun setFrameMaterialOption(option: Frametype) {
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

    fun setPhotoSizeOption(option: Photosize) {
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

        return framePrice + materialPrice + photoPrice + photoSizePrice
    }

    fun getTotalPrice() {
        viewModelScope.launch {
            shoppingCartRepository.getTotalPhotoPrice().collect { total ->
                _uiState.update { currentState ->
                    currentState.copy(
                        totalPrice = total ?: 0.0  // Default to 0.0 if null
                    )
                }
            }
        }
    }


    fun resetDetails() {
        val photo = defaultPhoto
        val frameType =  defaultArtFrametype
        val frameSize = FrameSize.entries[0].size
        val photoSize = defaultPhotoSize

        setPhoto(photo)
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