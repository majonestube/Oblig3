package com.example.oblig3.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.oblig3.R
import org.intellij.lang.annotations.PrintFormat

enum class ArtScreen (@StringRes val title: Int) {
    Start(title = R.string.main_title),
    Artist(title = R.string.velg_kunstner),
    Category(title = R.string.velg_kategori),
    Bilder(title = R.string.bilder),
    Detaljer(title = R.string.detaljer),
    Betaling(title = R.string.betaling)
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
                    onPayButtonClicked = { /*TODO*/ },
                )
            }

            composable (route = ArtScreen.Artist.name) {
                ArtistScreen()

            }

            composable (route = ArtScreen.Category.name) {
                CategoryScreen()
            }
        }
    }

}