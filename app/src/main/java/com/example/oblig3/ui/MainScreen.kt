package com.example.oblig3.ui

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oblig3.R
import com.example.oblig3.data.DataSource
import com.example.oblig3.data.SelectedPhoto
import kotlin.math.roundToInt

@Composable
fun MainScreen(
    picturesChosen: List<SelectedPhoto>,
    onArtistButtonClicked: () -> Unit,
    onCategoryButtonClicked: () -> Unit,
    onDeleteButtonClicked: (SelectedPhoto) -> Unit,
    onPayButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    totalPrice: Int
) {

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
                    onClick = { onArtistButtonClicked() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        stringResource(R.string.kunstner)
                    )
                }
                Spacer(modifier.width(dimensionResource(R.dimen.padding_small)))
                Button(
                    onClick = { onCategoryButtonClicked() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.kategori)
                    )
                }

            }
            Text(
                text = stringResource(R.string.antall_bilder_valgt, picturesChosen.size),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.totalpris_med_pris, totalPrice),
                fontWeight = FontWeight.Bold
            )
            if (picturesChosen.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    modifier = Modifier
                        .heightIn(min = 0.dp, max = LocalConfiguration.current.screenHeightDp.dp * 0.55f)
                ) {
                    items(picturesChosen) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(4.dp)) {
                                    val photo = DataSource.PhotosForSale.find { it.id == item.photoId }
                                    val artist = DataSource.Artists.find { it.id == item.photoId }
                                    if (photo != null) {
                                        Text(
                                            text = photo.title
                                        )
                                    if (artist != null) {
                                        Text(
                                            text = artist.name
                                        )
                                    }
                                }

                                }
                                Column(
                                    modifier = Modifier
                                        .weight(1f)) {
                                    Text(
                                        text = item.frameType.name
                                    )
                                    Text(
                                        text = item.photoSize.name
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .weight(1f)) {
                                    Text(
                                        text = item.frameWidth.toString()
                                    )
                                    Text(
                                        text = (item.photoPrice).toString()
                                    )
                                }
                                Button(
                                    onClick = {
                                        onDeleteButtonClicked(SelectedPhoto(
                                            photoId = item.photoId,
                                            frameType = item.frameType,
                                            frameWidth = item.frameWidth,
                                            photoSize = item.photoSize,
                                            photoPrice = item.photoPrice
                                        ))
                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.delete)
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
