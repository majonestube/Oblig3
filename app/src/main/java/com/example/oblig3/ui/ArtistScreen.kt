package com.example.oblig3.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.oblig3.R
import com.example.oblig3.data.Artist
import com.example.oblig3.data.DataSource

@Composable
fun ArtistScreen (
    onClick: (Long) -> Unit
) {
    val artists: List<Artist> = DataSource.Artists

    if (artists.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .heightIn(min = 0.dp, max = LocalConfiguration.current.screenHeightDp.dp * 0.55f)
        ) {
            items(artists) { item ->
                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_medium))
                        .clickable { onClick(item.id) },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
                    ) {
                        Text(
                            text = item.name
                        )
                        Text(
                            text = item.familyName
                        )
                    }
                }
            }
        }
    }
}