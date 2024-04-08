package com.example.lab2

enum class ReadingStatus {
    Not_started,
    Reading,
    Finished
}

class Book(
    val name: String,
    val genres: Array<String>,
    var status: ReadingStatus,
    val imageUrl: String) {

}