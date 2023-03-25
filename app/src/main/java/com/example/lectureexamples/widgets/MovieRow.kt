package com.example.lectureexamples.widgets

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lectureexamples.models.Movie

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