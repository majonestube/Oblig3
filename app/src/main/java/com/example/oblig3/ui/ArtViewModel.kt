package com.example.oblig3.ui

import androidx.lifecycle.ViewModel
import com.example.oblig3.data.ArtUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ArtViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ArtUiState())
    val uiState: StateFlow<ArtUiState> = _uiState.asStateFlow()

    // Set the selected artist
    fun setArtist(artistId: Long) {
        _uiState.update { currentState ->
            currentState.copy(
                chosenArtist = artistId
            )
        }
    }
}