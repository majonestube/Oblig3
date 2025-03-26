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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PaymentScreen(
    onClick: () -> Unit
) {
    val price = 667
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Totalpris: ",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = price.toString(),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Betalingsvalg"
        )
        Button(
            onClick = {
                Toast.makeText(context, "Ikke implementert", Toast.LENGTH_SHORT).show()
                onClick() },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Betal!"
            )
        }
    }
}