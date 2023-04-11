package com.example.lectureexamples.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Genre(
    val title: String,
){
    var isSelected: Boolean by mutableStateOf(true)

    companion object {
        fun values(): List<Genre> {
            return listOf(
                Genre(title = "Action"),
                Genre(title = "Comedy"),
                Genre(title = "Drama"),
                Genre(title = "Thriller"),
                Genre(title = "Adventure"),
                Genre(title = "Crime"),
                Genre(title = "History"),
                Genre(title = "Fantasy"),
                Genre(title = "Scifi"),

            )
        }
    }
}