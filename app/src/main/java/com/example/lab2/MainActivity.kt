package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab2.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var useDarkTheme by rememberSaveable { mutableStateOf(false) }
            fun toggleTheme() {
                useDarkTheme = !useDarkTheme
            }
            AppTheme(useDarkTheme) {
                MainScreen()
            }
        }
    }
}

@Preview
@Composable
fun MainScreen() {
    val bookViewModel = BooksViewModel()
    BoxWithConstraints {
        val navController = rememberNavController()

        if (maxWidth > 840.dp) {
            Row(modifier = Modifier.fillMaxSize()) {
                PermanentNavigationDrawerComponent(navController = navController, modifier = Modifier.width(250.dp))
                NavHost(navController = navController, startDestination = "bookList", modifier = Modifier.weight(1f)) {
                    composable("bookList") { BookListScreen(navController, bookViewModel) }
                    composable("addBook") { AddBookScreen(navController, AddBookViewModel(), bookViewModel) }
                    composable("userProfile") { UserProfileScreen(navController) }
                }
            }
        } else if (maxWidth > 600.dp) {
            Row(modifier = Modifier.fillMaxSize()) {
                NavigationRailComponent(navController = navController, modifier = Modifier.width(75.dp))
                NavHost(navController = navController, startDestination = "bookList", modifier = Modifier.weight(1f)) {
                    composable("bookList") { BookListScreen(navController, bookViewModel) }
                    composable("addBook") { AddBookScreen(navController, AddBookViewModel(), bookViewModel) }
                    composable("userProfile") { UserProfileScreen(navController) }
                }
            }
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                NavHost(navController = navController, startDestination = "bookList", modifier = Modifier.weight(1f)) {
                    composable("bookList") { BookListScreen(navController, bookViewModel) }
                    composable("addBook") { AddBookScreen(navController, AddBookViewModel(), bookViewModel) }
                    composable("userProfile") { UserProfileScreen(navController) }
                }
                BottomNavigationComponent(navController = navController)
            }
        }
    }
}

