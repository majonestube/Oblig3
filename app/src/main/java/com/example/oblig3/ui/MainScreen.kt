package com.example.oblig3.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.oblig3.R
import com.example.oblig3.data.Photo
import com.example.oblig3.ui.theme.bodyFontFamily

@Composable
fun MainScreen(
    onNextButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val testPhotoList: List<String> = listOf("1", "2", "3",
        "4", "5", "6", "7", "8",
        "9", "10", "11,", "12", "14", "4", "5", ",", ",", ",", ",", ",",
        "9", "10", "11,", "12", "14", "4", "5", ",", ",", ",", ",", ",",
        "9", "10", "11,", "12", "14", "4", "5", ",", ",", ",", ",", ",")

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
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        stringResource(R.string.kunstner)
                    )
                }
                Spacer(modifier.width(dimensionResource(R.dimen.padding_small)))
                Button(
                    onClick = { /*TODO*/ },
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
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = item)
                        }
                    }
                }
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text(
                    text = stringResource(R.string.til_betaling)
                )
            }
        }

    }
}