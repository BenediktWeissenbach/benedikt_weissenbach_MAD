package com.example.lectureexamples.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.lectureexamples.models.getMovies
import com.example.lectureexamples.widgets.MovieRow
import com.example.lectureexamples.widgets.simpleTopAppBar

@Composable
fun FavoritesScreen(navController: NavHostController) {

    Column() {
        simpleTopAppBar(text = "Movies", navController = navController)

        LazyColumn{

            items(getMovies().subList(0,2)) {movie ->
                MovieRow(movie = movie){
                        movieId -> navController.navigate("detail/$movieId")
                }
            }
        }
    }
}