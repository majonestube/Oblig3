package com.example.oblig3.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ArtViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ArtUiState())
}