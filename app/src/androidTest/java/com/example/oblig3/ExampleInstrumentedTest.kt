package com.example.oblig3

import android.provider.ContactsContract.Data
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.oblig3.data.Category
import com.example.oblig3.data.DataSource
import com.example.oblig3.data.FrameSize
import com.example.oblig3.data.Photo
import com.example.oblig3.ui.ArtScreen
import com.example.oblig3.ui.ArtdealerApp
import onNodeWithStringId


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import kotlin.math.exp

private lateinit var navController: TestNavHostController




/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.oblig3", appContext.packageName)
    }

    @Before
    fun setupNavHost(): Unit {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            ArtdealerApp(navController = navController)
        }
    }

    @Test
    fun verifyStartDestination() {
        navController.assertCurrentRouteName(ArtScreen.Start.name)
    }

    @Test
    fun verifyBackNavigationNotShownOnStartOrderScreen() {
        val backText = composeTestRule.activity.getString(R.string.back)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun verifyBackNavigationShownOnArtistScreen() {
        navigateToArtistScreen()
        val backText = composeTestRule.activity.getString(R.string.back)
        composeTestRule.onNodeWithContentDescription(backText).assertExists()
    }

    @Test
    fun verifyDetailsHomeButtonToHomeScreen() {
        navigateToCategoryScreen()
        navigateToFoodCategoryScreen()
        navigateToFirstPhotoInCategoryFood()
        navigateToHomeScreenFromHomeButton()
        navController.assertCurrentRouteName(ArtScreen.Start.name)
    }

    @Test
    fun verifyAddingPictureToShoppingCart() {
        val expectedString = composeTestRule.activity.getString(R.string.antall_bilder_valgt, 1)
        navigateToArtistScreen()
        navigateToArtistPictures()
        navigateToFirstPhotoOfFirstArtist()
        clickOptionsInDetailsScreen()
        composeTestRule.onNodeWithStringId(R.string.legg_i_handlekurv).performClick()

        navController.assertCurrentRouteName(ArtScreen.Start.name)
        composeTestRule.onNodeWithText(expectedString).assertExists()
    }

    @Test
    fun verifyPaymentScreenPaymentButton() {
        navigateToArtistScreen()
        navigateToArtistPictures()
        navigateToFirstPhotoOfFirstArtist()
        clickOptionsInDetailsScreen()
        composeTestRule.onNodeWithStringId(R.string.legg_i_handlekurv).performClick()
        composeTestRule.onNodeWithStringId(R.string.til_betaling).performClick()
        composeTestRule.onNodeWithStringId(R.string.betal).performClick()

        navController.assertCurrentRouteName(ArtScreen.Start.name)
        val expectedString = composeTestRule.activity.getString(R.string.antall_bilder_valgt, 0)
        composeTestRule.onNodeWithText(expectedString).assertExists()

    }

    private fun navigateToArtistScreen() {
        composeTestRule.onNodeWithStringId(R.string.kunstner)
            .performClick()
    }

    private fun navigateToArtistPictures() {
        val artist = DataSource.Artists
        composeTestRule.onNodeWithText(artist[0].name).performClick()
    }

    private fun navigateToCategoryScreen() {
        composeTestRule.onNodeWithStringId(R.string.kategori)
            .performClick()

    }


    private fun navigateToFoodCategoryScreen() {
        val category: List<Category> = DataSource.Categories
        composeTestRule.onNodeWithText(category[1].name)
            .performClick()
    }

    private fun navigateToFirstPhotoInCategoryFood() {
        val pictures: List<Photo> = DataSource.photosByCategory(Category.FOOD)
        composeTestRule.onNodeWithContentDescription(pictures[0].title)
            .performClick()

    }

    private fun navigateToFirstPhotoOfFirstArtist() {
        val artist = DataSource.Artists
        val pictures: List<Photo> = DataSource.photosByArtist(artist[0].id)
        composeTestRule.onNodeWithContentDescription(pictures[0].title)
            .performClick()
    }

    private fun navigateToHomeScreenFromHomeButton() {
        composeTestRule.onNodeWithStringId(R.string.hjem)
            .performClick()
    }

    private fun clickOptionsInDetailsScreen() {
        composeTestRule.onNodeWithStringId(R.string.rammetype_metal).performClick()
        composeTestRule.onNodeWithStringId(R.string.bildestørrelse_medium).performClick()
        composeTestRule.onNodeWithText(FrameSize.MEDIUM.toString()).performClick()
    }

}

