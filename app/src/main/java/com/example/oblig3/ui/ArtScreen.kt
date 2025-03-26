package com.example.oblig3.ui

import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.oblig3.R
import com.example.oblig3.data.Category

enum class ArtScreen (@StringRes val title: Int) {
    Start(title = R.string.main_title),
    Artist(title = R.string.velg_kunstner),
    Category(title = R.string.velg_kategori),
    PictureByArtist(title = R.string.bilder),
    PictureByCategory(title = R.string.bilder),
    Details(title = R.string.detaljer),
    Payment(title = R.string.betaling)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtdealerAppBar(
    currentScreen: ArtScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtdealerApp(
    viewModel: ArtViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ArtScreen.valueOf(
        backStackEntry?.destination?.route ?: ArtScreen.Start.name
    )

    Scaffold (
        topBar = {
            ArtdealerAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) {
            innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = ArtScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable (route = ArtScreen.Start.name) {
                MainScreen(
                    onArtistButtonClicked = { navController.navigate(ArtScreen.Artist.name) },
                    onCategoryButtonClicked = { navController.navigate(ArtScreen.Category.name) },
                    onPayButtonClicked = { navController.navigate(ArtScreen.Payment.name) },
                )
            }

            composable (route = ArtScreen.Artist.name) {
                ArtistScreen(
                    viewModel = viewModel,
                    onClick = {
                        navController.navigate(ArtScreen.PictureByArtist.name)
                    }
                )
            }

            composable (route = ArtScreen.Category.name) {
                CategoryScreen(
                    viewModel = viewModel,
                    onClick = {
                        navController.navigate(ArtScreen.PictureByCategory.name)
                    }
                )
            }

            composable (route = ArtScreen.PictureByArtist.name) {
                PicturesByArtistScreen(
                    artistId = uiState.chosenArtist
                )
            }

            composable (route = ArtScreen.PictureByCategory.name) {
                PicturesByCategoryScreen(
                    categoryId = uiState.chosenCategory
                )
            }

            composable(route = ArtScreen.Payment.name) {
                PaymentScreen(
                    onClick = {
                        navController.navigate(ArtScreen.Start.name)
                    }
                )
            }

        }
    }

}