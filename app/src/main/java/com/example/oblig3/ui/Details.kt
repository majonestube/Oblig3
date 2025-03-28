package com.example.oblig3.ui

import android.telecom.Call.Details
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
    chosenFrameType: FrameType,
    chosenFrameSize: Int,
    chosenPhotoSize: PhotoSize,
    onChoosePhotoSize: (PhotoSize) -> Unit,
    onChooseFrameType: (FrameType) -> Unit,
    onChooseFrameSize: (Int) -> Unit,
    onAddPhoto: () -> Unit,
    onDoneClick: () -> Unit,
    calculatePrice: Float
) {
    val context = LocalContext.current


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
                Box(modifier = Modifier.wrapContentSize()
                    .border(
                    width = chosenFrameSize.dp,
                    brush = SolidColor(chosenFrameType.color),
                    shape = CutCornerShape(12.dp),
                )
                    .padding(chosenFrameSize.dp)) {
                    Image(
                        painter = painterResource(photo.imageResId),
                        contentDescription = photo.title,
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                    )

                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.velg_ramme_og_st_rrelse),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        Column() {

            Row( verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)) {
                    RadioButton(
                        selected = chosenFrameType == FrameType.WOOD,
                        onClick = { onChooseFrameType(FrameType.WOOD)}
                    )
                    Text(
                        text = stringResource(R.string.rammetype_tre),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)) {
                    RadioButton(
                        selected = chosenPhotoSize == PhotoSize.SMALL,
                        onClick = {onChoosePhotoSize(PhotoSize.SMALL)})
                    Text(
                        text = stringResource(R.string.bildestørrelse_liten)
                    )
                }

            }
            Row( verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)) {
                    RadioButton(selected = chosenFrameType == FrameType.METAL,
                        onClick = {onChooseFrameType(FrameType.METAL)})
                    Text(
                        text = stringResource(R.string.rammetype_metal)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)) {
                    RadioButton(selected = chosenPhotoSize == PhotoSize.MEDIUM,
                        onClick = {onChoosePhotoSize(PhotoSize.MEDIUM)})
                    Text(
                        text = stringResource(R.string.bildestørrelse_medium)
                    )
                }
            }
            Row( verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)) {
                    RadioButton(selected = chosenFrameType == FrameType.PLASTIC,
                        onClick = {onChooseFrameType(FrameType.PLASTIC)})
                    Text(
                        text = stringResource(R.string.rammetype_plastikk)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)) {
                    RadioButton(selected = chosenPhotoSize == PhotoSize.LARGE,
                        onClick = {onChoosePhotoSize(PhotoSize.LARGE)})
                    Text(
                        text = stringResource(R.string.bildestørrelse_stor)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.velg_rammebredde),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        Row( verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)) {
                RadioButton(
                    selected = chosenFrameSize == FrameSize.SMALL.size,
                    onClick = {onChooseFrameSize(FrameSize.SMALL.size)})
                Text(
                    text = FrameSize.SMALL.size.toString()
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)) {
                RadioButton(
                    selected = chosenFrameSize == FrameSize.MEDIUM.size,
                    onClick = {onChooseFrameSize(FrameSize.MEDIUM.size)})
                Text(
                    text = FrameSize.MEDIUM.size.toString()
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)) {
                RadioButton(
                    selected = chosenFrameSize == FrameSize.LARGE.size,
                    onClick = {onChooseFrameSize(FrameSize.LARGE.size)})
                Text(
                    text = FrameSize.LARGE.size.toString()
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.detaljer_pris,calculatePrice.toString()),
                fontWeight = FontWeight.Bold
            )

        }
        Row() {
            Button(
                modifier = Modifier.weight(1f).padding(start = 8.dp, end = 4.dp),
                onClick = {
                    onAddPhoto()
                    Toast.makeText(context,
                        context.getString(R.string.lagt_i_handlekurv), Toast.LENGTH_SHORT).show()
                    onDoneClick()
                }
            ) {
                Text(
                    stringResource(R.string.legg_i_handlekurv))
            }
            Button(
                modifier = Modifier.weight(1f).padding(start = 4.dp,end = 8.dp),
                onClick = onDoneClick
            ) {
                Text(stringResource(R.string.hjem))
            }
        }
    }
}


@Composable
@Preview //In ArtScreen.kt -> ArtDealerApp -> NavHost, edit startDestination from "Start" to "Details"
fun DetailsPreview(
    photo: Photo = DataSource.PhotosForSale[0]
){
    Oblig3Theme(dynamicColor = false)  {
        ArtdealerApp(
            modifier = Modifier.fillMaxSize())
    }
}