package com.example.lab2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun BookListScreen(navController: NavController, viewModel: BooksViewModel) {
    val books = viewModel.books

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Список книг") },
            )
        }
    ) { padding ->
        BookList(books = books, modifier = Modifier.padding(padding))
    }
}

@Composable
fun BookList(books: MutableList<Book>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(books) { index, book ->
            BookRow(book = book, fun(): Book = books.removeAt(index) )
        }
    }
}

@Composable
fun BookRow(book: Book, removeBook: () -> Book, modifier: Modifier = Modifier, ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = rememberAsyncImagePainter(book.imageUrl), contentDescription = null, modifier = Modifier.size(60.dp))
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = book.name)
            Text(text = "Жанр: ${book.genres.joinToString(", ")}")
            Text(text = "Статус: ${book.status}")
        }
        IconButton(onClick = { removeBook() }) {
            Icon(Icons.Filled.Delete, contentDescription = "Видалити")
        }
    }
    Divider()
}

class BooksViewModel : ViewModel() {
    private val _books = mutableStateListOf<Book>(
        // Початковий список книг для прикладу
        Book("Книга 1", arrayOf("Фантастика"), ReadingStatus.Finished, "https://play-lh.googleusercontent.com/_tslXR7zUXgzpiZI9t70ywHqWAxwMi8LLSfx8Ab4Mq4NUTHMjFNxVMwTM1G0Q-XNU80"),
        Book("Книга 2", arrayOf("Наукова література"), ReadingStatus.Reading, "https://play-lh.googleusercontent.com/_tslXR7zUXgzpiZI9t70ywHqWAxwMi8LLSfx8Ab4Mq4NUTHMjFNxVMwTM1G0Q-XNU80")
    )
    val books: MutableList<Book> get() = _books

    fun addBook(name: String, genres: Array<String>, status: ReadingStatus, imageUrl: String) {
        val newBook = Book(name, genres, status, imageUrl)
        _books.add(newBook)
    }
}