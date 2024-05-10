package com.example.lab2

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bookListDisplaysBooks() {
        composeTestRule.setContent {
            val fakeNavController = rememberNavController()
            val viewModel = BooksViewModel()
            BookListScreen(navController = fakeNavController, viewModel = viewModel)
        }

        // Check if the books are displayed
        composeTestRule.onNodeWithText("Книга 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Книга 2").assertIsDisplayed()
    }

    @Test
    fun deleteBook() {
        composeTestRule.setContent {
            val fakeNavController = rememberNavController()
            val viewModel = BooksViewModel()
            BookListScreen(navController = fakeNavController, viewModel = viewModel)
        }

        composeTestRule.onAllNodesWithContentDescription("Видалити")[0].performClick()
        composeTestRule.onNodeWithText("Книга 1").assertDoesNotExist()
    }
}
