package com.example.oblig3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.oblig3.data.DataSource
import com.example.oblig3.data.Photo
import com.example.oblig3.network.ArtPhoto

@Composable
fun PicturesByArtistScreen(pictures: List<ArtPhoto>,
                           artistId: String,
                           onClick: (ArtPhoto) -> Unit
    ) {
    //val pictures: List<Photo> = DataSource.photosByArtist(artistId)

    if (pictures.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .heightIn(min = 0.dp, max = LocalConfiguration.current.screenHeightDp.dp * 0.55f)
        ) {
            items(pictures) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { onClick(item) }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = item.title
                            )
                            AsyncImage(
                                model = ImageRequest.Builder(context = LocalContext.current)
                                    .data(item.imgSrc)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = item.title
                            )
                        }
                    }
                }
            }
        }
    }
}