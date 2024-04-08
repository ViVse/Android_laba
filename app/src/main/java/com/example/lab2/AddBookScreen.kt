package com.example.lab2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class AddBookViewModel : ViewModel() {
    var bookName by mutableStateOf("")
    var selectedGenre by mutableStateOf("")
    var readingStatus by mutableStateOf(ReadingStatus.Not_started)
    var imgUrl by mutableStateOf("")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookScreen(navController: NavController, addViewModel: AddBookViewModel, booksViewModel: BooksViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Додати книгу") },
                actions = {
                    IconButton(onClick = { navController.navigate("bookList")}) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "На головну")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier=Modifier.padding(padding)) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp, 0.dp)) {
                TextField(
                    value = addViewModel.bookName,
                    onValueChange = { addViewModel.bookName = it },
                    label = { Text("Назва книги") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = addViewModel.imgUrl,
                    onValueChange = { addViewModel.imgUrl = it },
                    label = { Text("Посилання на зображення") },
                    modifier = Modifier.fillMaxWidth().padding(top=10.dp)
                )
                GenreDropdownMenu(
                    selectedGenre = addViewModel.selectedGenre,
                    onGenreSelect = { addViewModel.selectedGenre = it },
                    genres = listOf("Фантастика", "Історія", "Наукова література")
                )
                ReadingStatusSelector(
                    currentStatus = addViewModel.readingStatus,
                    onStatusSelect = { addViewModel.readingStatus = it }
                )
                Button(onClick = {
                    booksViewModel.addBook(addViewModel.bookName, arrayOf(addViewModel.selectedGenre), addViewModel.readingStatus, addViewModel.imgUrl)
                    navController.navigate("bookList")
                }) {
                    Text("Додати книгу")
                }
            }
        }
    }
}


@Composable
fun GenreDropdownMenu(selectedGenre: String, onGenreSelect: (String) -> Unit, genres: List<String>) {
    var expanded = remember { mutableStateOf(false) }
    val selectedText = if (selectedGenre.isEmpty()) "Виберіть жанр" else selectedGenre

    Column(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
        Text(text = "Жанр")
        Box(modifier = Modifier.fillMaxWidth().clickable(onClick = { expanded.value = true })) {
            Text(selectedText, modifier = Modifier.background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.09F)).fillMaxWidth().padding(5.dp, 15.dp))
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {
                genres.forEach { genre ->
                    DropdownMenuItem(
                        text = { Text(genre) },
                        onClick = {
                            onGenreSelect(genre)
                            expanded.value = false
                        })
                }
            }
        }
    }
}


@Composable
fun ReadingStatusSelector(currentStatus: ReadingStatus, onStatusSelect: (ReadingStatus) -> Unit) {
    Column {
        Text("Статус читання")
        ReadingStatus.values().forEach { status ->
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                .fillMaxWidth()
                .clickable { onStatusSelect(status) }) {
                RadioButton(
                    selected = status == currentStatus,
                    onClick = { onStatusSelect(status) }
                )
                Text(text = status.name)
            }
        }
    }
}