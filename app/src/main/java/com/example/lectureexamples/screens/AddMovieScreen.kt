package com.example.lectureexamples.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.lectureexamples.MovieViewModel
import com.example.lectureexamples.R
import com.example.lectureexamples.models.Genre
import com.example.lectureexamples.models.getMovies
import com.example.lectureexamples.widgets.MovieRow
import com.example.lectureexamples.widgets.simpleTopAppBar

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddMovieScreen(navController: NavHostController, movieViewModel: MovieViewModel) {

    fun validate(): Boolean {
        return true
    }

    var title by remember {
        mutableStateOf("")
    }
    var year by remember {
        mutableStateOf("")
    }
    var director by remember {
        mutableStateOf("")
    }
    var actors by remember {
        mutableStateOf("")
    }
    var plot by remember {
        mutableStateOf("")
    }
    var rating by remember {
        mutableStateOf("")
    }

    Column() {
        simpleTopAppBar(text = "AddMovie", navController = navController)

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Column() {

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    onValueChange = { value -> title = value },
                    label = { Text("Enter title") })
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = year,
                    onValueChange = { value -> year = value },
                    label = { Text("Enter year") })

                Text(text = "Select Genres")



                // https://moodle.fh-campuswien.ac.at/pluginfile.php/1164266/mod_page/content/7/AddMovieScreen.kt
                val genres = Genre.values()

                var genreItems by remember {
                    mutableStateOf(
                        genres.map { genre ->
                            ListItemSelectable(
                                title = genre.title,
                                isSelected = false
                            )
                        }
                    )
                }

                LazyHorizontalGrid(
                    modifier = Modifier.height(100.dp),
                    rows = GridCells.Fixed(3)
                ) {
                    items(genreItems) { genreItem ->
                        Chip(
                            modifier = Modifier.padding(2.dp),
                            colors = ChipDefaults.chipColors(
                                backgroundColor = if (genreItem.isSelected)
                                    colorResource(id = R.color.purple_200)
                                else
                                    colorResource(id = R.color.white)
                            ),
                            onClick = {
                                genreItems = genreItems.map {
                                    if (it.title == genreItem.title) {
                                        genreItem.copy(isSelected = !genreItem.isSelected)
                                    } else {
                                        it
                                    }
                                }
                            }
                        ) {
                            Text(text = genreItem.title)
                        }
                    }
                }

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = director,
                            onValueChange = { value -> director = value },
                            label = { Text("Enter director") })
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = actors,
                            onValueChange = { value -> actors = value },
                            label = { Text("Enter actors") })
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            value = plot,
                            onValueChange = { value -> plot = value },
                            label = { Text("Enter plot") })
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = rating,
                            onValueChange = { value -> rating = value },
                            label = { Text("Enter rating") })
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Add")
                        }
                }
            }
        }
    }

class ListItemSelectable(var title: String, var isSelected: Boolean) {
    fun copy(isSelected: Boolean): ListItemSelectable {
        return ListItemSelectable(title = title, isSelected)
    }
}