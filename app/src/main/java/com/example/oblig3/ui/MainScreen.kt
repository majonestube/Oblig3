package com.example.oblig3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.oblig3.R
import com.example.oblig3.data.Category
import com.example.oblig3.data.DataSource
import com.example.oblig3.data.FrameType
import com.example.oblig3.data.Photo
import com.example.oblig3.data.PhotoSize
import com.example.oblig3.data.SelectedPhoto

@Composable
fun MainScreen(
    onArtistButtonClicked: () -> Unit,
    onCategoryButtonClicked: () -> Unit,
    onPayButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val testPhotoList: List<SelectedPhoto> = listOf(
        SelectedPhoto(
            photoId = 1,
            frameType = FrameType.METAL,
            frameWidth = 30,
            photoSize = PhotoSize.MEDIUM,
            photoPrice = 0.6f
        ),
        SelectedPhoto(
            photoId = 4,
            frameType = FrameType.WOOD,
            frameWidth = 50,
            photoSize = PhotoSize.SMALL,
            photoPrice = 0.2f
        ),
        SelectedPhoto(
            photoId = 2,
            frameType = FrameType.PLASTIC,
            frameWidth = 20,
            photoSize = PhotoSize.LARGE,
            photoPrice = 1f
        )
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(R.dimen.padding_small),
                    end = dimensionResource(R.dimen.padding_small)
                ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(
                modifier = Modifier.height(dimensionResource(R.dimen.padding_small))
            )
            Text(
                text = stringResource(R.string.velg_bilde_basert_paa),
                fontWeight = FontWeight.Bold
            )
            Row {
                Button(
                    onClick = { onArtistButtonClicked() }, //TODO: Hardkodet "1" siden lambdaen krever (Int -> Unit)
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        stringResource(R.string.kunstner)
                    )
                }
                Spacer(modifier.width(dimensionResource(R.dimen.padding_small)))
                Button(
                    onClick = { onCategoryButtonClicked() },//TODO: Hardkodet "1" siden lambdaen krever (Int -> Unit)
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.kategori)
                    )
                }

            }
            Text(
                text = stringResource(R.string.antall_bilder_valgt, /*TODO*/),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.totalpris, /*TODO*/),
                fontWeight = FontWeight.Bold
            )
            if (testPhotoList.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    modifier = Modifier
                        .heightIn(min = 0.dp, max = LocalConfiguration.current.screenHeightDp.dp * 0.55f)
                ) {
                    items(testPhotoList) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column {
                                    Text(
                                        text = DataSource.PhotosForSale.filter { it.id == item.photoId }[0].title
                                    )
                                    Text(
                                        text = DataSource.Artists.filter { it.id == item.photoId }[0].name
                                    )
                                }
                                Column {
                                    Text(
                                        text = item.frameType.name
                                    )
                                    Text(
                                        text = item.photoSize.name
                                    )
                                }
                                Column {
                                    Text(
                                        text = item.frameWidth.toString()
                                    )
                                    Text(
                                        text = (item.photoPrice * DataSource.PHOTO_PRICE).toString()
                                    )
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete"
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Button(
                onClick = { onPayButtonClicked() },
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text(
                    text = stringResource(R.string.til_betaling)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen({},{},{})
}