package com.example.lectureexamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.layout.Arrangement.Top
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovies
import com.example.lectureexamples.navigation.Navigation
import com.example.lectureexamples.ui.theme.LectureExamplesTheme

class MainActivity : ComponentActivity() {

    protected val movieViewModel by viewModels<MovieViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LectureExamplesTheme {
                    Navigation(movieViewModel)
                }
            }
        }
    }

class MovieViewModel : ViewModel() {

    var moviesList: MutableList<MovieUI> = getMovies().map {
        MovieUI(
            id = it.id,
            initalFav = false,
            title = it.title,
            year = it.year,
            genre = it.genre,
            director = it.director,
            actors = it.actors,
            plot = it.plot,
            images = it.images,
            rating = it.rating
        )
    }.toMutableStateList();

    val movies: List<MovieUI>
        get() = moviesList

}

data class MovieUI(
    val id: String,
    var initalFav: Boolean,
    val title: String,
    val year: String,
    val genre: String,
    val director: String,
    val actors: String,
    val plot: String,
    val images: List<String>,
    val rating: String
){
    var fav: Boolean by mutableStateOf(initalFav)
}