package com.example.oblig3.ui


import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.oblig3.R
import com.example.oblig3.data.FrameSize
import com.example.oblig3.data.Frametype
import com.example.oblig3.data.Photo
import com.example.oblig3.data.Photosize
import com.example.oblig3.data.defaultArtFrametype
import com.example.oblig3.data.defaultPhotoSize
import com.example.oblig3.ui.theme.Oblig3Theme


@OptIn(ExperimentalStdlibApi::class)
@Composable
fun Details(
    photo: Photo,
    frameTypes: List<Frametype>,
    photoSizes: List<Photosize>,
    chosenFrameType: Frametype,
    chosenFrameSize: Int,
    chosenPhotoSize: Photosize,
    onChoosePhotoSize: (Photosize) -> Unit,
    onChooseFrameType: (Frametype) -> Unit,
    onChooseFrameSize: (Int) -> Unit,
    onAddPhoto: () -> Unit,
    onDoneClick: () -> Unit,
    calculatePrice: Double
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
                val color = "ff" + chosenFrameType.color.removePrefix("0x")
                Box(modifier = Modifier.wrapContentSize()
                    .border(
                    width = chosenFrameSize.dp,
                    brush = SolidColor(Color(color.hexToInt())),
                    shape = CutCornerShape(12.dp),
                )
                    .padding(chosenFrameSize.dp)) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(photo.imgSrc)
                            .crossfade(true)
                            .build(),
                        contentDescription = photo.title,
                        modifier = Modifier.fillMaxWidth(0.6f)
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
        Column {

            Row( verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)) {
                    RadioButton(
                        //selected = chosenFrameType == FrameType.WOOD,
                        //onClick = { onChooseFrameType(DFrameType.WOO)}
                        selected = chosenFrameType == frameTypes[0],
                        onClick = { onChooseFrameType(frameTypes[0])}
                    )
                    Text(
                        //text = stringResource(R.string.rammetype_tre),
                        text = frameTypes[0].name,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)) {
                    RadioButton(
                        selected = chosenPhotoSize == photoSizes[0],
                        onClick = {onChoosePhotoSize(photoSizes[0])}
                    )
                    Text(
                        text = photoSizes[0].name
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
                    RadioButton(selected = chosenFrameType == frameTypes[1],
                        onClick = {onChooseFrameType(frameTypes[1])})
                    Text(
                        text = frameTypes[1].name
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)) {
                    RadioButton(
                        selected = chosenPhotoSize == photoSizes[1],
                        onClick = {onChoosePhotoSize(photoSizes[1])})
                    Text(
                        text = photoSizes[1].name
                    )
                }
            }
            Row( verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)) {
                    RadioButton(selected = chosenFrameType == frameTypes[2],
                        onClick = {onChooseFrameType(frameTypes[2])})
                    Text(
                        text = frameTypes[2].name
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)) {
                    RadioButton(
                        selected = chosenPhotoSize == photoSizes[2],
                        onClick = {onChoosePhotoSize(photoSizes[2])})
                    Text(
                        text = photoSizes[2].name
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
        Row {
            Button(
                modifier = Modifier.weight(1f).padding(start = 8.dp, end = 4.dp),
                onClick = {
                    if (chosenFrameType == defaultArtFrametype || chosenPhotoSize == defaultPhotoSize) {

                        Toast.makeText(context,
                            context.getString(R.string.details_du_må_velge_options), Toast.LENGTH_LONG).show()

                    } else {
                        onAddPhoto()
                        Toast.makeText(context,
                            context.getString(R.string.lagt_i_handlekurv), Toast.LENGTH_SHORT).show()
                        onDoneClick()
                    }

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

