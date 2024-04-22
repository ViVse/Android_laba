package com.example.lab2.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppTheme(useDarkTheme: Boolean, content: @Composable () -> Unit) {
//    val LightColorScheme = lightColorScheme(
//        primary = Color(0xFF6200EE),
//        onPrimary = Color.White,
//        primaryContainer = Color(0xFF3700B3),
//        onPrimaryContainer = Color.White,
//        secondary = Color(0xFF03DAC6),
//        onSecondary = Color.Black,
//        secondaryContainer = Color(0xFF018786),
//        onSecondaryContainer = Color.White,
//        background = Color(0xFFF6F6F6),
//        onBackground = Color.Black,
//        surface = Color.White,
//        onSurface = Color.Black,
//        error = Color(0xFFB00020),
//        onError = Color.White,
//        // Додайте інші кольори за потреби
//    )
//
//    val DarkColorScheme = darkColorScheme(
//        primary = Color(0xFFBB86FC),
//        onPrimary = Color.Black,
//        primaryContainer = Color(0xFF3700B3),
//        onPrimaryContainer = Color.Black,
//        secondary = Color(0xFF03DAC6),
//        onSecondary = Color.White,
//        secondaryContainer = Color(0xFF03DAC6),
//        onSecondaryContainer = Color.Black,
//        background = Color(0xFF121212),
//        onBackground = Color.White,
//        surface = Color(0xFF121212),
//        onSurface = Color.White,
//        error = Color(0xFFCF6679),
//        onError = Color.Black,
//        // Додайте інші кольори за потреби
//    )
//
//    val colors = if (useDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme() {
        Surface(modifier = Modifier.fillMaxSize()){
            content()
        }
    }
}