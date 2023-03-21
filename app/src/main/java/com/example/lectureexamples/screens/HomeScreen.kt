package com.example.lectureexamples.screens

import android.service.autofill.OnClickAction
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lectureexamples.R
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovies
import com.example.lectureexamples.ui.theme.LectureExamplesTheme
import androidx.compose.ui.res.imageResource
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun HomeScreen(navController: NavHostController) {
    LectureExamplesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column {
                //Greeting()
                Text(
                    style = MaterialTheme.typography.h6,
                    text= "Movie List"
                )
                MyList(navController= navController)
            }
            //MyList()
            //Greeting()
            //WelcomeText(modifier = Modifier.padding(16.dp), text = "welcome to my app!")
        }
    }
}

@Composable
fun MyList(movies: List<Movie> = getMovies(), navController: NavHostController){

    LazyColumn{

        items(movies) {movie ->
            MovieRow(movie = movie){
                    movieId -> navController.navigate("detailscreen/$movieId")
            }
        }
    }
}


@Composable
fun MovieRow(movie: Movie, onClick: (String) -> Unit = {}) {

    var expanded by remember {
        mutableStateOf(false)
    }

    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )


    Card(modifier = Modifier
        .clickable { onClick(movie.id) }
        .fillMaxWidth()
        .padding(8.dp)
        .animateContentSize(
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        ),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
    ) {
        Column() {

            Box(modifier = Modifier
                .height(180.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter,
            ) {

                AsyncImage(
                    model = movie.images[0],
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                // Add a gradient on top of the image
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                                startY = 220f,
                                endY = 450f
                            )
                        )
                )

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ){
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites",
                    )
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clickable { expanded = !expanded },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(movie.title, style = MaterialTheme.typography.h6, color = Color.White)
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Show details",
                        tint = Color.White,
                        modifier = Modifier.rotate(rotationState)
                    )
                }

            }

            if (expanded) {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Text(text = "Director: " + movie.director)
                    Text(text = "Year: " + movie.year)
                    Text(text = "Genre: " + movie.genre)
                    Text(text = "Actors: " + movie.actors)
                    Text(text = "Rating: " + movie.rating)
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = movie.plot)
                }
            }
        }

    }
}

@Preview
@Composable
fun WelcomeText(modifier: Modifier = Modifier, text: String = "default") {
    Row(
        modifier = modifier
            .padding(16.dp)
            .background(Color.Blue)
            .fillMaxWidth()
    ) {
        Text(modifier = modifier, text = "Hola")
        Text(text = text)
    }

}

@Preview
@Composable
fun Greeting() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember {
            mutableStateOf("")
        }

        Text(text = "Hello ${name}!")

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it},
            label = { Text("Name")}
        )


        /*
        // step 2 - add a mutableStateOf to fire the event for recomposition

       var name = mutableStateOf("")   // use a state holder to register changes
        // var name  by mutableStateOf("")
        Text(text = "Hello ${name.value}!")   // get value of state holder object

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },    // change its value accordingly
            label = { Text("Name")}
        )
        */



        /*
        // step 3 - use remember
        var name by remember {         // use remember to skip overwriting after first composition
            mutableStateOf("")
        }

        Text(text = "Hello ${name}!")

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name")}
        )

         */
    }
}