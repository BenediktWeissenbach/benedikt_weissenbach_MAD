package com.example.lectureexamples.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lectureexamples.MovieViewModel
import com.example.lectureexamples.screens.AddMovieScreen
import com.example.lectureexamples.screens.DetailScreen
import com.example.lectureexamples.screens.FavoritesScreen
import com.example.lectureexamples.screens.HomeScreen

@Composable
fun Navigation(movieViewModel: MovieViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(navController = navController, movieViewModel = movieViewModel)
        }

        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId"){
                type = NavType.StringType
            })
        ) {backStackEntry ->
            DetailScreen(navController, movieId = backStackEntry.arguments?.getString("movieId"), movieViewModel = movieViewModel)
        }
        composable(route = "favorites") { FavoritesScreen(navController = navController, movieViewModel = movieViewModel) }

        composable(route = "add"){AddMovieScreen(navController = navController, movieViewModel = movieViewModel)}
    }
}