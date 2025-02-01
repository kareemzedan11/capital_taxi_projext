package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun startTripDesign() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(5.dp, Color.Transparent, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {


        // The requestAndCash section stays at the bottom
        requestAndCash(modifier = Modifier.align(Alignment.BottomCenter)) // Stay fixed at the bottom
    }
}




