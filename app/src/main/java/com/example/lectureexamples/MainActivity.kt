package com.example.lectureexamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovies
import com.example.lectureexamples.navigation.Navigation
import com.example.lectureexamples.ui.theme.LectureExamplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LectureExamplesTheme {
                var expanded by remember {
                    mutableStateOf(false)
                }
                //https://foso.github.io/Jetpack-Compose-Playground/material/topappbar/
                Column {
                    TopAppBar(
                        elevation = 4.dp,
                        title = {
                            Text("I'm a TopAppBar")
                        },
                        backgroundColor = MaterialTheme.colors.primarySurface, actions = {
                            Box() {
                                IconButton(onClick = {expanded = true}) {
                                    Icon(Icons.Filled.MoreVert, null)
                                }
                                //https://semicolonspace.com/dropdown-menu-jetpack-compose/#dropdown
                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = {
                                        expanded = false
                                    }
                                ){
                                    Text(text = "test")
                                }
                            }
                        })
                    Navigation()
                }
            }
        }
    }
}