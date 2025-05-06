package com.example.astronomic

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "planet_list") {
        composable("planet_list") {
            PlanetListScreen(navController)
        }
        composable("planet/{name}") { backStackEntry ->
            val planetName = backStackEntry.arguments?.getString("name") ?: ""
            PlanetDetailScreen(planetName)
        }
    }
}