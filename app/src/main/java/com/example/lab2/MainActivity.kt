package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var useDarkTheme by rememberSaveable { mutableStateOf(false) }
            fun toggleTheme() {
                useDarkTheme = !useDarkTheme
            }
            AppTheme(useDarkTheme) {
                MainScreen(toggleTheme = { toggleTheme() })
            }
        }
    }
}

@Composable
fun AppTheme(useDarkTheme: Boolean, content: @Composable () -> Unit) {
    val LightColorScheme = lightColorScheme(
        primary = Color(0xFF6200EE),
        onPrimary = Color.White,
        primaryContainer = Color(0xFF3700B3),
        onPrimaryContainer = Color.White,
        secondary = Color(0xFF03DAC6),
        onSecondary = Color.Black,
        secondaryContainer = Color(0xFF018786),
        onSecondaryContainer = Color.White,
        background = Color(0xFFF6F6F6),
        onBackground = Color.Black,
        surface = Color.White,
        onSurface = Color.Black,
        error = Color(0xFFB00020),
        onError = Color.White,
        // Додайте інші кольори за потреби
    )

    val DarkColorScheme = darkColorScheme(
        primary = Color(0xFFBB86FC),
        onPrimary = Color.Black,
        primaryContainer = Color(0xFF3700B3),
        onPrimaryContainer = Color.Black,
        secondary = Color(0xFF03DAC6),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFF03DAC6),
        onSecondaryContainer = Color.Black,
        background = Color(0xFF121212),
        onBackground = Color.White,
        surface = Color(0xFF121212),
        onSurface = Color.White,
        error = Color(0xFFCF6679),
        onError = Color.Black,
        // Додайте інші кольори за потреби
    )

    val colors = if (useDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(colorScheme = colors) {
        Surface(modifier = Modifier.fillMaxSize()){
            content()
        }
    }
}

@Composable
fun MainScreen(toggleTheme: () -> Unit) {
    val navController = rememberNavController()
    val bookViewModel = BooksViewModel()
    NavHost(navController = navController, startDestination = "bookList") {
        composable("bookList") { BookListScreen(navController, bookViewModel, toggleTheme) }
        composable("addBook") { AddBookScreen(navController, AddBookViewModel(), bookViewModel) }
        composable("userProfile") { UserProfileScreen(navController) }
    }
}
