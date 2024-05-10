package com.example.lab2

import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BooksViewModelTest {

    private lateinit var viewModel: BooksViewModel

    @Before
    fun setup() {
        viewModel = BooksViewModel()
    }

    @Test
    fun `addBook adds a new book to the books list`() {
        val initialSize = viewModel.books.size
        val newBook = Book("New Book", arrayOf("Genre"), ReadingStatus.Not_started, "http://example.com/book.jpg")

        viewModel.addBook(newBook.name, newBook.genres, newBook.status, newBook.imageUrl)
        Snapshot.sendApplyNotifications()

        assert(initialSize + 1 == viewModel.books.size , { "Book list size should increase by 1" })
        assert(newBook.name == viewModel.books.last().name,
            { "The last book in the list should be the new book added" })
    }

    @Test
    fun `removeBook removes a book by index`() {
        val initialSize = viewModel.books.size

        viewModel.removeBook(0)
        Snapshot.sendApplyNotifications()

        assert(initialSize - 1 == viewModel.books.size , { "Book list size should decrease by 1" })
        assert(viewModel.books[0].name == "Книга 2",
            { "The method should delete correct book" })
    }
}
