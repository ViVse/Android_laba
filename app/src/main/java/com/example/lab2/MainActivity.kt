package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.lab2.ui.theme.Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val books = listOf(
            Book("Book 1", arrayOf("history", "fantasy"), ReadingStatus.Reading, "https://play-lh.googleusercontent.com/_tslXR7zUXgzpiZI9t70ywHqWAxwMi8LLSfx8Ab4Mq4NUTHMjFNxVMwTM1G0Q-XNU80"),
            Book("Book 2", arrayOf("romance", "long"), ReadingStatus.Not_started, "https://play-lh.googleusercontent.com/_tslXR7zUXgzpiZI9t70ywHqWAxwMi8LLSfx8Ab4Mq4NUTHMjFNxVMwTM1G0Q-XNU80"),
            Book("Book 3", arrayOf("action", "war"), ReadingStatus.Finished, "https://play-lh.googleusercontent.com/_tslXR7zUXgzpiZI9t70ywHqWAxwMi8LLSfx8Ab4Mq4NUTHMjFNxVMwTM1G0Q-XNU80"),
            Book("Book 4", arrayOf("adventure"), ReadingStatus.Reading, "https://play-lh.googleusercontent.com/_tslXR7zUXgzpiZI9t70ywHqWAxwMi8LLSfx8Ab4Mq4NUTHMjFNxVMwTM1G0Q-XNU80"),
            Book("Book 5", arrayOf("detective"), ReadingStatus.Finished, "https://play-lh.googleusercontent.com/_tslXR7zUXgzpiZI9t70ywHqWAxwMi8LLSfx8Ab4Mq4NUTHMjFNxVMwTM1G0Q-XNU80"),
            Book("Book 6", arrayOf("classic", "horror"), ReadingStatus.Not_started, "https://play-lh.googleusercontent.com/_tslXR7zUXgzpiZI9t70ywHqWAxwMi8LLSfx8Ab4Mq4NUTHMjFNxVMwTM1G0Q-XNU80"),
        )
        setContent {
            Lab2Theme {
                Column {
                    Text(text = "Something to read", fontSize = 25.sp)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        ) {
                        items(books.filter { book -> book.status != ReadingStatus.Finished }.sortedBy { it -> it.status }) {
                                book -> BookCard(book = book)
                        }
                    }
                    Text(text = "Finished", fontSize = 25.sp)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                    ) {
                        items(books.filter { book -> book.status == ReadingStatus.Finished }) {
                                book -> BookCard(book = book)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookCard(book: Book) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(10.dp)
    ) {
        Image(painter = rememberAsyncImagePainter(book.imageUrl), contentDescription = null, modifier = Modifier.size(60.dp))
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            BookStatus(status = book.status)
            Text(text = book.name, fontSize = 20.sp, modifier = Modifier.fillMaxSize())
            BookGenres(genres = book.genres)
        }       
    }
}

@Composable
fun BookStatus(status: ReadingStatus) {
    val color = when(status) {
        ReadingStatus.Reading -> Color.Blue
        ReadingStatus.Finished -> Color.Red
        ReadingStatus.Not_started -> Color.DarkGray
    }

    Text(
        text = status.toString(),
        color = Color.White,
        modifier = Modifier
            .background(color = color)
            .padding(2.dp)

    )
}

@Composable
fun BookGenres(genres: Array<String>) {
    val sortedGenres = genres.sortedBy { it }

    LazyRow(modifier = Modifier.width(500.dp)) {
        items(sortedGenres) { genre ->
            Text(text = genre, color = Color.DarkGray)
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
}