package com.example.oblig3.ui

import androidx.annotation.StringRes
import com.example.oblig3.R

enum class ArtUiState (@StringRes val title: Int) {
    Start(title = R.string.main_title),
    Artist(title = R.string.velg_kunstner),
    Category(title = R.string.velg_kategori),
    Bilder(title = R.string.bilder),
    Detaljer(title = R.string.detaljer),
    Betaling(title = R.string.betaling)
}