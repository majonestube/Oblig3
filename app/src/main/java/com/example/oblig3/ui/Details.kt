package com.example.oblig3.ui

import android.telecom.Call.Details
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.oblig3.R
import com.example.oblig3.data.DataSource
import com.example.oblig3.data.FrameSize
import com.example.oblig3.data.FrameType
import com.example.oblig3.data.Photo
import com.example.oblig3.data.PhotoSize
import com.example.oblig3.data.SelectedPhoto
import com.example.oblig3.ui.theme.Oblig3Theme


@Composable
fun Details(
    photo: Photo,
    onAddPhoto: () -> Unit,
    onClick: () -> Unit,
    viewModel: ArtViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val chosenFrameType = uiState.chosenFrameMaterial
    val chosenFrameSize = uiState.chosenFrameSize
    val chosenPhotoSize = uiState.chosenPhotoSize

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = photo.title
                )
                Image(
                    painter = painterResource(photo.imageResId),
                    contentDescription = photo.title,
                    modifier = Modifier.fillMaxWidth(0.4f),
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.velg_ramme_og_st_rrelse)
        )
        Column() {
            Row( verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)) {
                RadioButton(
                    selected = chosenFrameType == FrameType.WOOD,
                    onClick = {viewModel.setFrameMaterialOption(FrameType.WOOD)}
                )
                Text(
                    text = stringResource(R.string.rammetype_tre)
                )
                RadioButton(
                    selected = chosenPhotoSize == PhotoSize.SMALL,
                    onClick = {viewModel.setPhotoSizeOption(PhotoSize.SMALL)})
                Text(
                    text = stringResource(R.string.bildestørrelse_liten)
                )
            }
            Row( verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                RadioButton(selected = chosenFrameType == FrameType.METAL,
                    onClick = {viewModel.setFrameMaterialOption(FrameType.METAL)})
                Text(
                    text = stringResource(R.string.rammetype_metal)
                )
                RadioButton(selected = chosenPhotoSize == PhotoSize.MEDIUM,
                    onClick = {viewModel.setPhotoSizeOption(PhotoSize.MEDIUM)})
                Text(
                    text = stringResource(R.string.bildestørrelse_medium)
                )
            }
            Row( verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                RadioButton(selected = chosenFrameType == FrameType.PLASTIC,
                    onClick = {viewModel.setFrameMaterialOption(FrameType.PLASTIC)})
                Text(
                    text = stringResource(R.string.rammetype_plastikk)
                )
                RadioButton(selected = chosenPhotoSize == PhotoSize.LARGE,
                    onClick = {viewModel.setPhotoSizeOption(PhotoSize.LARGE)})
                Text(
                    text = stringResource(R.string.bildestørrelse_stor)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.velg_rammebredde)
        )
        
        Row( verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            RadioButton(
                selected = chosenFrameSize == FrameSize.SMALL.size,
                onClick = {viewModel.setFrameSizeOption(FrameSize.SMALL.size)})
            Text(
                text = FrameSize.SMALL.size.toString()
            )
            RadioButton(
                selected = chosenFrameSize == FrameSize.MEDIUM.size,
                onClick = {viewModel.setFrameSizeOption(FrameSize.MEDIUM.size)})
            Text(
                text = FrameSize.MEDIUM.size.toString()
            )
            RadioButton(
                selected = chosenFrameSize == FrameSize.LARGE.size,
                onClick = {viewModel.setFrameSizeOption(FrameSize.LARGE.size)})
            Text(
                text = FrameSize.LARGE.size.toString()
            )
        }
        Row() {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    onAddPhoto()
                    Toast.makeText(context,
                        context.getString(R.string.lagt_i_handlekurv), Toast.LENGTH_SHORT).show()
                    onClick()
                }
            ) {
                Text(stringResource(R.string.legg_i_handlekurv))
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ }
            ) {
                Text(stringResource(R.string.hjem))
            }
        }
    }
}


@Composable
@Preview
fun DetailsPreview(
    photo: Photo = DataSource.PhotosForSale[0]
){
    Oblig3Theme(dynamicColor = false)  {
        ArtdealerApp(
            modifier = Modifier.fillMaxSize())
    }
}