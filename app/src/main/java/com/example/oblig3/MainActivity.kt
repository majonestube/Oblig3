package com.example.oblig3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.oblig3.ui.theme.Oblig3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Oblig3Theme(dynamicColor = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtComposable(
                        onClick = {/*TODO*/},
                        modifier = Modifier.padding(innerPadding),

                    )
                }
            }
        }
    }
}

@Composable
fun ArtComposable(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Row {
        Text(
            text = "Kunsthandler"
        )
        Button(
            onClick = onClick
        ){
            Text("Test")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Oblig3Theme(dynamicColor = false)  {
        ArtComposable(
            onClick = {/*TODO*/},
            modifier = Modifier.fillMaxSize()
        )
    }
}