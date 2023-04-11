package com.example.lectureexamples.widgets

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.lectureexamples.MovieViewModel


@Composable
fun MyList(navController: NavHostController, movieViewModel: MovieViewModel) {

    LazyColumn() {

        items(movieViewModel.moviesList) { movie ->
            MovieRow(movie = movie) { movieId ->
                navController.navigate("detail/$movieId")
            }
        }
    }
}