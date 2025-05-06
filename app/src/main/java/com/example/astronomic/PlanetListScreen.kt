package com.example.astronomic

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetListScreen(navController: NavHostController) {
    val planets = listOf("Marte", "Júpiter", "Saturno")

    Scaffold(
        topBar = { TopAppBar(title = { Text("Planetas") }) }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(planets) { planet ->
                ListItem(
                    headlineContent = { Text(planet) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("planet/${planet}")
                        }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Divider()
            }
        }
    }
}
