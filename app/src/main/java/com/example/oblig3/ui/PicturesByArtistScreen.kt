package com.example.oblig3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.oblig3.data.DataSource
import com.example.oblig3.data.Photo

@Composable
fun PicturesByArtistScreen(
    artistId: Long,
    onClick: (Photo) -> Unit
    ) {
    val pictures: List<Photo> = DataSource.photosByArtist(artistId)

    if (pictures.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .heightIn(min = 0.dp, max = LocalConfiguration.current.screenHeightDp.dp * 0.55f)
        ) {
            items(pictures) { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onClick(item) },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(item.imageResId) ,
                        contentDescription = item.title,
                        modifier = Modifier.fillMaxWidth(0.4f)
                    )
                }
            }
        }
    }
}