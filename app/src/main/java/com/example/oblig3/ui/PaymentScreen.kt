package com.example.oblig3.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.oblig3.R

@Composable
fun PaymentScreen(
    price: Int,
    onPayButtonClicked: () -> Unit,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.totalpris),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = price.toString(),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(R.string.betalingsvalg)
        )
        Button(
            onClick = {
                Toast.makeText(context,
                    context.getString(R.string.ikke_implementert), Toast.LENGTH_SHORT).show()
                onPayButtonClicked()
                onClick() },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(R.string.betal)
            )
        }
    }
}