package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun homeScreenDesign(navController: NavController) {

    var isDrawerOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
        // Google Map (Base Layer)
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            DrawerScreen(navController)
        }
    }
}
