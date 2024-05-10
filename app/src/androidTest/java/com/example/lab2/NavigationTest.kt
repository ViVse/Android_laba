package com.example.lab2

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Test
    fun navigateToAddBookScreen() {
        val navController = createTestNavHostController()
        composeTestRule.activity.setTestContent {
            MainScreen(navController = navController)
        }

        composeTestRule.onNodeWithContentDescription("Додати книгу").performClick()

        assert(navController.currentDestination?.route == "addBook")
    }

    @Test
    fun navigateToUserProfileScreen() {
        val navController = createTestNavHostController()
        composeTestRule.activity.setTestContent {
            MainScreen(navController = navController)
        }

        composeTestRule.onNodeWithContentDescription("Профіль").performClick()

        assert(navController.currentDestination?.route == "userProfile")
    }

    private fun createTestNavHostController(): TestNavHostController {
        val navController = TestNavHostController(composeTestRule.activity.applicationContext).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }
        return navController
    }
}
